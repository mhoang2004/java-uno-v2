import java.io.*;
import java.util.*;
import java.security.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private static final String CSV_PATH = "../resources/csv/data.csv";
    private static final String SALT = "random_salt_here";

    public static void main(String[] args) {
        // add new user
        addNewUserData("Suu", "123321");

        // print all users
        List<Map<String, String>> users = getAllUsersData();

        for (Map<String, String> row : users) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                System.out.printf("%-12s: %-10s", entry.getKey(), entry.getValue());
            }
            System.out.println();
        }

        System.out.println(checkValidLogIn("Teo", "abc123"));

        updateBestScoreById(1, 19);
    }

    public static void addNewUserData(String username, String password) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("bestScore", "0");

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data.put("date", date.format(formatter));

        writeDataToCSV(data);
    }

    public static void writeDataToCSV(Map<String, String> rowData) {
        int id = getAllUsersData().size();
        rowData.put("id", String.valueOf(id + 1));

        // endcode password
        String password = rowData.get("password");
        String hashedPassword = encodePassword(password);
        rowData.put("password", hashedPassword);

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_PATH, true))) {
            StringBuilder rowBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : rowData.entrySet()) {
                rowBuilder.append(entry.getValue()).append(",");
            }
            rowBuilder.deleteCharAt(rowBuilder.length() - 1); // Remove trailing comma

            writer.println(rowBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataToCSV(List<Map<String, String>> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_PATH))) {
            for (Map<String, String> row : data) {
                StringBuilder rowBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : row.entrySet()) {
                    rowBuilder.append(entry.getValue()).append(",");
                }
                rowBuilder.deleteCharAt(rowBuilder.length() - 1); // Remove trailing comma
                writer.println(rowBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> getAllUsersData() {
        String path = CSV_PATH;
        BufferedReader reader = null;
        String line = "";

        List<Map<String, String>> data = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Map<String, String> rowData = new LinkedHashMap<>();

                rowData.put("username", row[0]);
                rowData.put("password", row[1]);
                rowData.put("bestScore", row[2]);
                rowData.put("date", row[3]);
                rowData.put("id", row[4]);

                data.add(rowData);
            }

            // Print the data
            // for (Map<String, String> row : data) {
            // for (Map.Entry<String, String> entry : row.entrySet()) {
            // System.out.printf("%-12s: %-10s", entry.getKey(), entry.getValue());
            // }
            // System.out.println();
            // }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(SALT.getBytes());
            byte[] hashedPassword = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean checkPassword(String password, String encodedPassword) {
        String hashedPassword = encodePassword(password);
        return hashedPassword.equals(encodedPassword);
    }

    public static boolean checkValidLogIn(String usernameString, String password) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String username = row.get("username");
            String encodedPassword = row.get("password");

            if (username.equals(usernameString)) {
                if (checkPassword(password, encodedPassword)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void updateBestScoreById(int idToUpdate, int newBestScore) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            if (Integer.parseInt(row.get("id")) == idToUpdate) {
                row.put("bestScore", String.valueOf(newBestScore));
                break;
            }
        }

        writeDataToCSV(data);
    }
}
