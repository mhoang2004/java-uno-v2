import java.util.Objects;
import java.util.Random;

public class MyAccount {
	String userName;
	String email;
	String Password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MyAccount(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		Password = password;
	}

	public int hashCode() {
		return Objects.hash(Password, email, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAccount other = (MyAccount) obj;
		return Objects.equals(Password, other.Password) && Objects.equals(email, other.email)
				&& Objects.equals(userName, other.userName);
	}

	MyAccount checkPassword(String tmpPassword) {
		return Password.equals(tmpPassword) ? this : null;
	}
}
