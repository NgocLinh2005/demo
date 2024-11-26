import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Character.isDigit;

public class Bank {
    private List<Customer> customerList;

    /**
     * Constructor.
     */
    public Bank() {
        customerList = new ArrayList<>();
    }

    /**
     * readCustomerList.
     */
    public void readCustomerList(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            Customer now = null;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(" ");
                if (!isDigit(part[0].charAt(0))) {
                    StringBuilder name = new StringBuilder();
                    for (int i = 0; i < part.length - 2; i++) {
                        name.append(part[i]).append(" ");
                    }
                    name.append(part.length - 2);
                    long idNumber = Long.parseLong(part[part.length - 1]);
                    now = new Customer(idNumber, name.toString());
                    customerList.add(now);
                } else {
                    long idAcc = Long.parseLong(part[0]);
                    double balanceAcc = Double.parseDouble(part[2]);
                    String typeAcc = part[1].toString();
                    Account current = null;
                    if (typeAcc.equals("CHECKING")) {
                        current = new CheckingAccount(idAcc, balanceAcc);
                    } else {
                        current = new SavingsAccount(idAcc, balanceAcc);
                    }
                    now.addAccount(current);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getCustomerInfoByNameOrder.
     */
    public String getCustomersInfoByNameOrder() {
        List<Customer> result = new ArrayList<>(customerList);
        result.sort(Comparator.comparing(Customer::getFullName));

        StringBuilder stringResult = new StringBuilder();
        for (Customer customer : result) {
            stringResult.append(customer.getCustomerInfo()).append("\n");
        }
        return stringResult.toString();
    }

    /**
     * getCustomerInfoByIdOrder.
     */
    public String getCustomersInfoByIdOrder() {
        List<Customer> result = new ArrayList<>(customerList);
        result.sort(Comparator.comparingLong(Customer::getIdNumber));

        StringBuilder stringResult = new StringBuilder();
        for (Customer customer : result) {
            stringResult.append(customer.getCustomerInfo()).append("\n");
        }
        return stringResult.toString();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }


}
