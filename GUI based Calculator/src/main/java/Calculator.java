import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.event.KeyEvent;
class SimpleCalculator {
    public static double calculate(String equation) {
        equation = equation.replaceAll(" ", "");
        if (equation.contains("inv")) {
            equation = equation.replace("inv", "");
            return 1 / evaluateLeftToRight(equation);
        }
        if (equation.contains("sqrt")) {
            equation = equation.replace("sqrt", "");
            return Math.sqrt(evaluateLeftToRight(equation));
        }
        return evaluateLeftToRight(equation);
    }
    private static double evaluateLeftToRight(String expression) {
        StringBuilder numBuffer = new StringBuilder();
        double result = 0;
        char lastOperator = '+';

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                numBuffer.append(currentChar);
            } else {
                if (numBuffer.length() > 0) {
                    double currentNumber = Double.parseDouble(numBuffer.toString());
                    result = applyLeftToRight(result, currentNumber, lastOperator);
                    numBuffer.setLength(0);
                }
                lastOperator = currentChar;
            }
        }
        if (numBuffer.length() > 0) {
            double currentNumber = Double.parseDouble(numBuffer.toString());
            result = applyLeftToRight(result, currentNumber, lastOperator);
        }

        return result;
    }
    private static double applyLeftToRight(double result, double currentNumber, char operator) {
        switch (operator) {
            case '+':
                return result + currentNumber;
            case '-':
                return result - currentNumber;
            case '*':
                return result * currentNumber;
            case '/':
                return result / currentNumber;
            case '%':
                return result % currentNumber;
            default:
                return result;
        }
    }
}
class HistoryNode{
    String equation;
    String result;

    public HistoryNode(String equation, String result) {
        this.equation = equation;
        this.result = result;
    }
}

public class Calculator extends JFrame{
    ArrayList<HistoryNode> history = new ArrayList<>();
    private JPanel calculatorPanel;
    private JLabel equationLabel;
    private JLabel resultLabel;
    private JButton MCButton;
    private JButton MRButton;
    private JButton MSButton;
    private JButton mPlusButton;
    private JButton mMinusButton;
    private JButton backSpaceButton;
    private JButton ButtonFor7;
    private JButton ButtonFor4;
    private JButton CEButton;
    private JButton cButton;
    private JButton plusMinusButton;
    private JButton sqrtButton;
    private JButton modulusButton;
    private JButton ButtonFor8;
    private JButton ButtonFor9;
    private JButton divideButton;
    private JButton inverseButton;
    private JButton multiplyButton;
    private JButton ButtonFor6;
    private JButton ButtonFor5;
    private JButton ButtonFor1;
    private JButton ButtonFor2;
    private JButton ButtonFor3;
    private JButton ButtonFor0;
    private JButton decimalButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton equalsToButton;
    private JPanel scientificPanel;
    private JPanel programmer1Panel;
    private JPanel programmer2Panel;
    private JLabel countLabel;
    private JPanel statisticsPanel;
    private JPanel standardPanel;
    private JPanel unitConversionPanel;
    private JPanel datePanel;
    private JPanel mortagePanel;
    private JPanel vehicleLeasePanel;
    private JPanel fuelEconomyMPGPanel;
    private JPanel fuelEconomyL100KmPanel;
    String equation = "";
    public Calculator(){
        setContentPane(calculatorPanel);
        setSize(400,300);
        setTitle("k225186 Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        equalsToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText( String.valueOf( SimpleCalculator.calculate( equation ) ) );
                String historyEquation = equationLabel.getText();
                String historyResult = resultLabel.getText();
                history.add( new HistoryNode(historyEquation,historyResult) );
            }
        });
        ButtonFor0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="0";
                equationLabel.setText(equation);
            }
        });
        ButtonFor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="1";
                equationLabel.setText(equation);
            }
        });
        ButtonFor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="2";
                equationLabel.setText(equation);
            }
        });
        ButtonFor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="3";
                equationLabel.setText(equation);
            }
        });
        ButtonFor4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="4";
                equationLabel.setText(equation);
            }
        });
        ButtonFor5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="5";
                equationLabel.setText(equation);
            }
        });
        ButtonFor6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="6";
                equationLabel.setText(equation);
            }
        });
        ButtonFor7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="7";
                equationLabel.setText(equation);
            }
        });
        ButtonFor8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="8";
                equationLabel.setText(equation);
            }
        });
        ButtonFor9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation+="9";
                equationLabel.setText(equation);
            }
        });
        backSpaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equation.length() > 0) {
                    if (equation.length() == 1) {
                        equation = "";
                    }
                    else {
                        if(equation.charAt(equation.length()-1) == 'v'){
                            equation = equation.substring(0,equation.length()-3);
                        }
                        else {
                            equation = equation.substring(0, equation.length() - 1);
                        }
                    }
                    equationLabel.setText(equation);
                }
            }
        });
        CEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = "";
                resultLabel.setText("");
                equationLabel.setText(equation);
            }
        });
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = "";
                resultLabel.setText("");
                equationLabel.setText(equation);
            }
        });
        plusMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = equation.length() -1 ; i >= 0 ; i++) {
                    if(equation.charAt(i) == '+' ||
                            equation.charAt(i) == '-' ||
                            equation.charAt(i) == '/' ||
                            equation.charAt(i) == '*' ||
                            equation.charAt(i) == '%' ){
                        equation = equation.substring(0, i) + '-' + equation.substring(i+1);
                        break;
                    }
                }
                equationLabel.setText(equation);
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "/";
                equationLabel.setText(equation);
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "*";
                equationLabel.setText(equation);
            }
        });
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "-";
                equationLabel.setText(equation);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "+";
                equationLabel.setText(equation);
            }
        });
        sqrtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = "sqrt" + equation;
                equationLabel.setText(equation);
            }
        });
        modulusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "%";
                equationLabel.setText(equation);
            }
        });
        inverseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += "inv";
                equationLabel.setText(equation);
            }
        });
        decimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation += ".";
                equationLabel.setText(equation);
            }
        });

        setUpMenuBar();
        setAllPanelToInvisible();
        setVisible(true);
    }
    public void setAllPanelToInvisible(){
        scientificPanel.setVisible(false);
        programmer1Panel.setVisible(false);
        programmer2Panel.setVisible(false);
        statisticsPanel.setVisible(false);
        countLabel.setVisible(false);
        unitConversionPanel.setVisible(false);
        datePanel.setVisible(false);
        mortagePanel.setVisible(false);
        vehicleLeasePanel.setVisible(false);
        fuelEconomyMPGPanel.setVisible(false);
        fuelEconomyL100KmPanel.setVisible(false);
    }
    public void setUpMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu viewMenu = new JMenu("View");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem standardFromViewMenu = new JMenuItem("Standard");
        standardFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                standardPanel.setVisible(true);
            }
        });
        standardFromViewMenu.setMnemonic(KeyEvent.VK_1);

        JMenuItem scientificFromViewMenu = new JMenuItem("Scientific");
        scientificFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                scientificPanel.setVisible(true);
            }
        });
        scientificFromViewMenu.setMnemonic(KeyEvent.VK_2);

        JMenuItem programmerFromViewMenu = new JMenuItem("Programmer");
        programmerFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                programmer1Panel.setVisible(true);
                programmer2Panel.setVisible(true);
            }
        });
        programmerFromViewMenu.setMnemonic(KeyEvent.VK_3);

        JMenuItem statisticsFromViewMenu = new JMenuItem("Statistics");
        statisticsFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                standardPanel.setVisible(false);
                statisticsPanel.setVisible(true);
                countLabel.setVisible(true);
            }
        });
        statisticsFromViewMenu.setMnemonic(KeyEvent.VK_4);

        JMenuItem historyFromViewMenu = new JMenuItem("History");
        historyFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder historyResult = new StringBuilder("History: \n");
                for(HistoryNode val : history){
                    historyResult.append(val.equation).append(" = ").append(val.result).append("\n");
                }
                JOptionPane.showMessageDialog(null, historyResult.toString());
            }
        });
        historyFromViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem digitGroupingFromViewMenu = new JMenuItem("Digit Grouping");

        JMenuItem basicFromViewMenu = new JMenuItem("Basic");
        basicFromViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem unitConversionFromViewMenu = new JMenuItem("Unit Conversion");
        unitConversionFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                unitConversionPanel.setVisible(true);
            }
        });
        unitConversionFromViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem dateCalculationFromViewMenu = new JMenuItem("Date Calculation");
        dateCalculationFromViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                datePanel.setVisible(true);
            }
        });
        dateCalculationFromViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

        JMenu worksheetFromViewMenu = new JMenu("Worksheet");

        JMenuItem copyFromEditMenu = new JMenuItem("Copy");
        copyFromEditMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem pasteFromEditMenu = new JMenuItem("Paste");
        pasteFromEditMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem historyDropdownFromEditMenu = new JMenuItem("History");

        JMenuItem viewHelpFromHelpMenu = new JMenuItem("View Help");

        JMenuItem aboutCalcFromHelpMenu = new JMenuItem("About Calculator");


        JMenuItem mortageFromWorksheetsAndViewMenu = new JMenuItem("Mortage");
        mortageFromWorksheetsAndViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                mortagePanel.setVisible(true);
            }
        });

        JMenuItem vehicleLeaseFromWorksheetsAndViewMenu = new JMenuItem("Vehicle Lease");
        vehicleLeaseFromWorksheetsAndViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                vehicleLeasePanel.setVisible(true);
            }
        });

        JMenuItem fuelEconomyMPGFromWorksheetsAndViewMenu = new JMenuItem("Fuel economy (mpg)");
        fuelEconomyMPGFromWorksheetsAndViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                fuelEconomyMPGPanel.setVisible(true);
            }
        });

        JMenuItem fuelEconomyL100kmFromWorksheetsAndViewMenu = new JMenuItem("Fuel economy (L/100 km)");
        fuelEconomyL100kmFromWorksheetsAndViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllPanelToInvisible();
                fuelEconomyL100KmPanel.setVisible(true);
            }
        });

        viewMenu.add(standardFromViewMenu);
        viewMenu.add(scientificFromViewMenu);
        viewMenu.add(programmerFromViewMenu);
        viewMenu.add(statisticsFromViewMenu);
        viewMenu.addSeparator();
        viewMenu.add(historyFromViewMenu);
        viewMenu.add(digitGroupingFromViewMenu);
        viewMenu.addSeparator();
        viewMenu.add(basicFromViewMenu);
        viewMenu.add(unitConversionFromViewMenu);
        viewMenu.add(dateCalculationFromViewMenu);

        worksheetFromViewMenu.add(mortageFromWorksheetsAndViewMenu);
        worksheetFromViewMenu.add(vehicleLeaseFromWorksheetsAndViewMenu);
        worksheetFromViewMenu.add(fuelEconomyMPGFromWorksheetsAndViewMenu);
        worksheetFromViewMenu.add(fuelEconomyL100kmFromWorksheetsAndViewMenu);

        viewMenu.add(worksheetFromViewMenu);

        editMenu.add(copyFromEditMenu);
        editMenu.add(pasteFromEditMenu);
        editMenu.addSeparator();
        editMenu.add(historyDropdownFromEditMenu);

        helpMenu.add(viewHelpFromHelpMenu);
        helpMenu.addSeparator();
        helpMenu.add(aboutCalcFromHelpMenu);

        menuBar.add(viewMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }
    public static void main(String[] args) {
        new Calculator();
    }
}
