import java.io.*;
import java.util.*;
import java.security.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private static final String CSV_PATH = "../resources/csv/data.csv";
    private static final String SALT = "random_salt_here";

    // public static void main(String[] args) {
    // // add new user
    // addNewUserData("Suu", "123321");

    // // print all users
    // List<Map<String, String>> users = getAllUsersData();

    // for (Map<String, String> row : users) {
    // for (Map.Entry<String, String> entry : row.entrySet()) {
    // System.out.printf("%-12s: %-10s", entry.getKey(), entry.getValue());
    // }
    // System.out.println();
    // }

    // System.out.println(checkValidLogIn("c@gmail.com", "123"));

    // updateBestScoreById(1, 19);
    // }

    public static void addNewUserData(String email, String password) {
        Map<String, String> data = new LinkedHashMap<>();
        int id = getAllUsersData().size();

        data.put("id", String.valueOf(id + 1));
        data.put("email", email);
        data.put("password", password);
        data.put("username", "Bro");
        data.put("bestScore", "0");
        data.put("isSound", "true");
        data.put("backGround", App.backroundGame);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data.put("date", date.format(formatter));

        writeDataToCSV(data);
    }

    public static void writeDataToCSV(Map<String, String> rowData) {
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

                rowData.put("id", row[0]);
                rowData.put("email", row[1]);
                rowData.put("password", row[2]);
                rowData.put("username", row[3]);
                rowData.put("bestScore", row[4]);
                rowData.put("isSound", row[5]);
                rowData.put("backGround", row[6]);
                rowData.put("date", row[7]);

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

    public static boolean checkValidLogIn(String emailString, String password) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
            String encodedPassword = row.get("password");

            if (email.equals(emailString)) {
                if (checkPassword(password, encodedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkValidSingOut(String emailString) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");

            if (email.equals(emailString)) {
                return false;
            }
        }
        return true;
    }
    public static String getUsername(String emailString) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                    return row.get("username");

            }
        }
        return null;
    }
    public static void setUsername(String emailString, String username) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                row.put("username", username);
                System.out.println(row.put("username", username));
            }
        }
        writeDataToCSV(data);
    }
    public static String getPathABackroung(String emailString) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                System.out.println(row.get("backGround"));
                    return row.get("backGround");

            }
        }
        return null;
    }
    public static void setPathABackroung(String emailString, String path) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                row.put("backGround", path);
            }
        }
        writeDataToCSV(data);
    }
    public static boolean getSounnd(String emailString) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                if(row.get("backGround").equals("true"))
                {
                    return true;
                }else{
                   return false;
                }

            }
        }
        return false;
    }
    public static void setSounnd(String emailString, boolean isOn) {
        List<Map<String, String>> data = getAllUsersData();

        for (Map<String, String> row : data) {
            String email = row.get("email");
           // String email = row.get("email");
            if (email.equals(emailString)) {
                if(isOn == true)
                {
                    row.put("backGround", "true");
                }else{
                    row.put("backGround", "false");
                }
            }
        }
        writeDataToCSV(data);
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