#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <limits.h>
#include <stdbool.h>
#include <string.h>

#define ROWS 21
#define COLS 23

typedef struct State {
    int row;
    int col;
    double g_cost;
    double f_cost;
    struct State* parent;
} State;

typedef struct Node {
    State* state;
    struct Node* next;
} Node;

typedef struct {
    Node* head;
    pthread_mutex_t lock;
} PriorityQueue;

double grid[ROWS][COLS];
pthread_mutex_t goalStateLock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t printLock = PTHREAD_MUTEX_INITIALIZER; // Mutex for controlling output printing
double totalCost = 0; // Variable to store the total cost
bool goalReached = false; // Flag to indicate if goal state is reached and printed

PriorityQueue* createpq() {
    PriorityQueue* pq = (PriorityQueue*)malloc(sizeof(PriorityQueue));
    pq->head = NULL;
    pthread_mutex_init(&pq->lock, NULL);
    return pq;
}

void enqueue(PriorityQueue* pq, State* state) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->state = state;
    newNode->next = NULL;

    pthread_mutex_lock(&pq->lock);

    if (pq->head == NULL || state->f_cost < pq->head->state->f_cost) {
        newNode->next = pq->head;
        pq->head = newNode;
    } else {
        Node* current = pq->head;
        while (current->next != NULL && current->next->state->f_cost <= state->f_cost) {
            current = current->next;
        }
        newNode->next = current->next;
        current->next = newNode;
    }

    pthread_mutex_unlock(&pq->lock);
}

State* dequeue(PriorityQueue* pq) {
    pthread_mutex_lock(&pq->lock);

    if (pq->head == NULL) {
        pthread_mutex_unlock(&pq->lock);
        return NULL;
    }

    Node* temp = pq->head;
    State* state = temp->state;
    pq->head = pq->head->next;
    free(temp);

    pthread_mutex_unlock(&pq->lock);
    return state;
}

bool isSafe(int row, int col) {
    return row >= 0 && row < ROWS && col >= 0 && col < COLS && grid[row][col] != 0;
}

State goalState;
double heuristic(int row, int col) {
    pthread_mutex_lock(&goalStateLock);
    double result = abs(row - goalState.row) + abs(col - goalState.col);
    pthread_mutex_unlock(&goalStateLock);
    return result;
}

void printPath(State* state) {
    if (state == NULL) {
        return;
    }
    printPath(state->parent);
    printf("(%d, %d) -> ", state->row, state->col);
}

void* search(void* arg) {
    PriorityQueue* pq = (PriorityQueue*)arg;
    int moveRow[] = {-1, 0, 1, 0};
    int moveCol[] = {0, 1, 0, -1};

    while (!goalReached) { // Loop until goal state is reached and printed
        State* state = dequeue(pq);

        if (state == NULL) {
            return NULL;
        }

        pthread_mutex_lock(&goalStateLock);
        goalReached = (state->row == goalState.row && state->col == goalState.col);
        pthread_mutex_unlock(&goalStateLock);

        if (goalReached) {
            pthread_mutex_lock(&printLock); // Acquire print lock before printing
            printf("Goal state reached at (%d, %d)\n", state->row, state->col);
            printf("Path: ");
            printPath(state);
            printf("End\n");
            // Update and print total cost
            totalCost = state->g_cost;
            printf("Total Cost: %.2lf\n", totalCost);
            pthread_mutex_unlock(&printLock); // Release print lock after printing
            exit(0);
            return NULL;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = state->row + moveRow[i];
            int newCol = state->col + moveCol[i];
            if (isSafe(newRow, newCol)) {
                State* newState = (State*)malloc(sizeof(State));
                newState->row = newRow;
                newState->col = newCol;
                newState->g_cost = state->g_cost + grid[newRow][newCol];
                newState->f_cost = newState->g_cost + heuristic(newState->row, newState->col);
                newState->parent = state;
                enqueue(pq, newState);
            }
        }
    }
    return NULL;
}

void readGridFromFile(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(1);
    }

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (fscanf(file, "%lf", &grid[i][j]) != 1) {
                fprintf(stderr, "Error reading from file at position (%d, %d)\n", i, j);
                fclose(file);
                exit(1);
            }
        }
    }

    fclose(file);
}
void displayLocations(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(1);
    }

    char line[256]; // Assuming max line length is 255 characters
    while (fgets(line, sizeof(line), file) != NULL) {
        // Remove trailing newline character, if present
        size_t len = strlen(line);
        if (len > 0 && line[len - 1] == '\n') {
            line[len - 1] = '\0';
        }
        printf("%s\n", line);
    }

    fclose(file);
}

int main() {
    readGridFromFile("map.txt");
    PriorityQueue* pq = createpq();
    
    displayLocations("locations.txt");	
    printf("Enter source coordinates\n");
    int s1,s2;
    scanf("%d %d", &s1,&s2);
    
    printf("Enter destination coordinates\n");
    int d1,d2;
    scanf("%d %d", &d1,&d2);
    
    
    State startState = {s1, s2, 0, 0, NULL};
    goalState = (State){d1, d2, 0, 0, NULL};

    startState.f_cost = startState.g_cost + heuristic(startState.row, startState.col);
    startState.parent = NULL;
    enqueue(pq, &startState);

    int num_thread = 4;
    pthread_t threads[num_thread];

    for (int i = 0; i < num_thread; i++) {
        pthread_create(&threads[i], NULL, search, pq);
    }

    for (int i = 0; i < num_thread; i++) {
        pthread_join(threads[i], NULL);
    }

    while (pq->head != NULL) {
        State* state = dequeue(pq);
        if (state != NULL)
            free(state);
    }
    pthread_mutex_destroy(&pq->lock);
    free(pq);

    return 0;
}

