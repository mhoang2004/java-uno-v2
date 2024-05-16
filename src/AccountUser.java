public class AccountUser {
    String mailAccount;
    String passwAccount;
    boolean isSound;
    AccountUser(String mailAccount , char[] password)
    {
        this.mailAccount = new String(mailAccount);
        this.passwAccount = new String(password);
    }
    void setUsername(String path) {
        FileHandler.setUsername(mailAccount, path);
    }
    String getUserName()
    {
        return  new String(FileHandler.getUsername(mailAccount));
    }
    String getMail(){
        System.out.println(mailAccount);
        return mailAccount;
    }
    String getPathBackround()
    {
        return FileHandler.getPathABackroung(mailAccount);
    }
    void setBackground(String path) {
        FileHandler.setPathABackroung(mailAccount, path);
    }
}
