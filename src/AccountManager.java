import java.util.HashMap;

public class AccountManager {
    private HashMap<String, MyAccount> ListAccount;

    AccountManager() {
        ListAccount = new HashMap<>();
        MyAccount me = new MyAccount("ON GIA BAO", "Giabaoonthcs123@gmail.com", "Giabao123@");
        MyAccount adminPA = new MyAccount("TRAN PHUONG ANH", "6351071002@st.edu.vn", "6351071002@st.edu.vn");
        MyAccount adminMH = new MyAccount("LE MINH HOANG", "6351071025@st.edu.vn", "6351071025@st.edu.vn");
        MyAccount me2 = new MyAccount("ON GIA BAO", "Giabaoonthcs123@gmail.com", "1");
        ListAccount.put("1", me2);
        ListAccount.put("Giabao123@", me);
        ListAccount.put("6351071025@st.edu.vn", adminMH);
        ListAccount.put("6351071002@st.edu.vn", adminPA);
    }

    HashMap<String, MyAccount> getListAccount() {
        return ListAccount;
    }
}
