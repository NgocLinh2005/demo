import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private long idNumber;
    private String fullName;
    private List<Account> accountList;

    public Customer() {
    }

    /**
     * constructor.
     */
    public Customer(long idName, String fullName) {
        this.idNumber = idName;
        this.fullName = fullName;
        accountList = new ArrayList<Account>();
    }

    /**
     * getcustomerinfo.
     */
    public String getCustomerInfo() {
        return ("Số CMND: " + idNumber + ". Họ tên: " + fullName + ".");
    }

    /**
     * addAccount.
     */
    public void addAccount(Account account) {
        accountList.add(account);
    }

    /**
     * removeAccount.
     */
    public void removeAccount(Account account) {
        if (accountList != null) {
            accountList.remove(account);
        }
    }

    //getter, setter
    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }
}

