import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Envelope {
    String platform;
    String webUrl;
    String email;
    String phoneNum;
    String password;

    Envelope(String platform, String webUrl, String email, String phoneNum, String password) {
        this.platform = platform;
        this.webUrl = webUrl;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

 
    private String show(String value) {
        return value.isEmpty() ? "(none)" : value;
    }

    public String displayEnvelope() {
        return "Platform: " + platform
                + "\nWebsite URL: " + show(webUrl)
                + "\nEmail: " + show(email)
                + "\nPhone Number: " + show(phoneNum)
                + "\nPassword: " + password;
    }
}


class Vault {
    String vaultName;
    int passcode;
    ArrayList<Envelope> envelopes;

    Vault(String vaultName, int passcode) {
        this.vaultName = vaultName;
        this.passcode = passcode;
        this.envelopes = new ArrayList<Envelope>();
    }
}

public class passwordVault {

    static final int MAX_ENVELOPES = 1000;

    static Scanner scanner = new Scanner(System.in);

    
    static ArrayList<Vault> vaults = new ArrayList<>();

    
    //  SCREEN HELPERS


   
    static void showTitle() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("                                           _                    _ _   ");
        System.out.println(" _ __   __ _ ___ _____      _____  _ __ __| | __   ____ _ _   _| | |_ ");
        System.out.println("| '_ \\ / _` / __/ __\\ \\ /\\ / / _ \\| '__/ _` | \\ \\ / / _` | | | | | __|");
        System.out.println("| |_) | (_| \\__ \\__ \\\\ V  V / (_) | | | (_| |  \\ V / (_| | |_| | | |_ ");
        System.out.println("| .__/ \\__,_|___/___/ \\_/\\_/ \\___/|_|  \\__,_|   \\_/ \\__,_|\\__,_|_|\\__|");
        System.out.println("|_|                                                                   ");
        System.out.println();
    }


    static void header(String text) {
        showTitle();
        System.out.println("== " + text + " ==\n");
    }

    
    static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    

    
    static String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    static String readNonEmpty(String prompt) {
        String text = readLine(prompt);
        while (text.isEmpty()) {
            text = readLine("This cannot be empty. Please try again:");
        }
        return text;
    }

   
    static int readInt(String prompt, int min, int max) {
        while (true) {
            String text = readLine(prompt);
            try {
                int number = Integer.parseInt(text);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number.");
            }
        }
    }

   
    static boolean confirm(String question) {
        while (true) {
            String answer = readLine(question + " (Y/N)");
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Please type Y or N.");
        }
    }

    
    static String readPasswordTwice() {
        String first = readNonEmpty("Enter the password:");
        String second = readNonEmpty("Enter the password again to lock in:");
        while (!first.equals(second)) {
            second = readNonEmpty("Password mismatch. Enter the password again:");
        }
        return second;
    }

    //  VAULT HELPERS
   

    static boolean isNameTaken(String name, int ignoreIndex) {
        for (int i = 0; i < vaults.size(); i++) {
            if (i != ignoreIndex && vaults.get(i).vaultName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    static String readUniqueVaultName(String prompt, int ignoreIndex) {
        String name = readNonEmpty(prompt);
        while (isNameTaken(name, ignoreIndex)) {
            name = readNonEmpty("A vault named \"" + name + "\" already exists. Choose a different name:");
        }
        return name;
    }

    static void listVaults() {
        for (int i = 0; i < vaults.size(); i++) {
            System.out.println((i + 1) + ". " + vaults.get(i).vaultName);
        }
    }

    static int selectVault(String action) {
        if (vaults.isEmpty()) {
            System.out.println("No vaults available.");
            pause();
            return -1;
        }
        listVaults();
        int number = readInt("\nEnter the number of the vault you want to " + action + " (0 to cancel):",
                0, vaults.size());
        return number - 1; 
    }

    static boolean checkPasscode(int index) {
        Vault vault = vaults.get(index);
        for (int attemptsLeft = 3; attemptsLeft > 0; attemptsLeft--) {
            int code = readInt("Enter the 4 digit passcode for " + vault.vaultName + ":", 0, 9999);
            if (code == vault.passcode) {
                return true;
            }
            System.out.println("Wrong passcode. Attempts left: " + (attemptsLeft - 1));
        }
        System.out.println("Too many wrong attempts.");
        pause();
        return false;
    }

    //  MAIN MENU OPTIONS
  

    // 1. Create a vault
    static void createVault() {
        header("CREATE A NEW VAULT");
        String name = readUniqueVaultName("Enter the vault name:", -1);

        int first = readInt("Create a 4 digit passcode for your vault\n:: this cannot be changed later ::",
                1000, 9999);
        int second = readInt("Enter the passcode again to lock in:", 0, 9999);
        while (second != first) {
            second = readInt("Passcode mismatch. Enter the passcode again:", 0, 9999);
        }

        vaults.add(new Vault(name, first));
        System.out.println("\nYour vault \"" + name + "\" has been created successfully!");
        pause();
    }

    // 2. View all vaults
    static void viewVaults() {
        header("YOUR VAULTS");
        if (vaults.isEmpty()) {
            System.out.println("No vaults available.");
        } else {
            listVaults();
        }
        pause();
    }

    // 3. Delete a vault
    static void deleteVault() {
        header("DELETE A VAULT");
        int index = selectVault("delete");
        if (index == -1) {
            return;
        }

        if (!checkPasscode(index)) {
            return;
        }

        String name = vaults.get(index).vaultName;
        if (confirm("\nAre you sure you want to delete \"" + name + "\"?\n"
                + ":: All its envelopes and credentials will be lost forever ::")) {
            vaults.remove(index);
            System.out.println("Vault deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
        pause();
    }

    // 4. Edit a vault (rename it)
    static void editVault() {
        header("EDIT A VAULT");
        int index = selectVault("edit");
        if (index == -1) {
            return;
        }

        if (!checkPasscode(index)) {
            return;
        }

        String newName = readUniqueVaultName("Enter the new vault name:", index);
        vaults.get(index).vaultName = newName;
        System.out.println("Vault name updated to \"" + newName + "\".");
        pause();
    }

    // 5. Search vaults by name
    static void searchVault() {
        header("SEARCH VAULTS");
        if (vaults.isEmpty()) {
            System.out.println("No vaults available.");
            pause();
            return;
        }

        String keyword = readNonEmpty("Enter the name (or part of the name) of the vault to search for:");
        boolean found = false;
        System.out.println();
        for (int i = 0; i < vaults.size(); i++) {
            if (vaults.get(i).vaultName.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + ". " + vaults.get(i).vaultName);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vault found matching \"" + keyword + "\".");
        }
        pause();
    }

    // 6. Open a vault 
    static void openVault() {
        header("OPEN A VAULT");
        if (vaults.isEmpty()) {
            System.out.println("No vaults available.");
            pause();
            return;
        }

        String input = readLine("Enter the name of the vault you want to open\n(or just press Enter to go back):");
        if (input.isEmpty()) {
            return;
        }

        int index = findVaultIndex(input);
        if (index == -1) {
            System.out.println("No single vault matches \"" + input + "\".");
            pause();
            return;
        }

        System.out.println("Vault found: " + vaults.get(index).vaultName);
        if (checkPasscode(index)) {
            vaultMenu(index);
        }
    }

    static int findVaultIndex(String text) {
        int partialMatch = -1;
        int partialCount = 0;
        for (int i = 0; i < vaults.size(); i++) {
            String name = vaults.get(i).vaultName;
            if (name.equalsIgnoreCase(text)) {
                return i;
            }
            if (name.toLowerCase().contains(text.toLowerCase())) {
                partialMatch = i;
                partialCount++;
            }
        }
        return partialCount == 1 ? partialMatch : -1;
    }

    

    static void vaultMenu(int index) {
        boolean inside = true;
        while (inside) {
            header("VAULT: " + vaults.get(index).vaultName);
            System.out.println("Vault Menu:\n1. Create a new envelope\n2. View envelope list\n3. Delete envelope"
                    + "\n4. Edit envelope\n5. Search envelope\n6. Open an envelope\n7. Return to main menu");
            int choice = readInt("\nChoose an option (1-7):", 1, 7);

            switch (choice) {
                case 1:
                    createEnvelope(index);
                    break;
                case 2:
                    viewEnvelopes(index);
                    break;
                case 3:
                    deleteEnvelope(index);
                    break;
                case 4:
                    editEnvelope(index);
                    break;
                case 5:
                    searchEnvelope(index);
                    break;
                case 6:
                    openEnvelope(index);
                    break;
                case 7:
                    inside = false;
                    break;
            }
        }
    }

    static int selectEnvelope(int index, String action) {
        ArrayList<Envelope> list = vaults.get(index).envelopes;
        if (list.isEmpty()) {
            System.out.println("No envelopes in this vault.");
            pause();
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).platform);
        }
        int number = readInt("\nEnter the number of the envelope you want to " + action + " (0 to cancel):",
                0, list.size());
        return number - 1; // 0 becomes -1 (cancel)
    }

    // 1. Create an envelope
    static void createEnvelope(int index) {
        header("CREATE AN ENVELOPE");
        ArrayList<Envelope> list = vaults.get(index).envelopes;

        if (list.size() >= MAX_ENVELOPES) {
            System.out.println("This vault is full (maximum " + MAX_ENVELOPES + " envelopes).");
            pause();
            return;
        }

        String platform = readNonEmpty("Enter the platform name:");
        String webUrl = readLine("Enter the website url (press Enter to skip):");
        String email = readLine("Enter the email linked with the platform (press Enter to skip):");
        String phone = readLine("Enter the phone number linked with the platform (press Enter to skip):");
        String password = readPasswordTwice();

        list.add(new Envelope(platform, webUrl, email, phone, password));
        System.out.println("\nEnvelope \"" + platform + "\" added!");
        pause();
    }

    // 2. View the envelope list
    static void viewEnvelopes(int index) {
        header("ENVELOPES IN " + vaults.get(index).vaultName);
        ArrayList<Envelope> list = vaults.get(index).envelopes;

        if (list.isEmpty()) {
            System.out.println("No envelopes in this vault.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).platform);
            }
        }
        pause();
    }

    // 3. Delete an envelope
    static void deleteEnvelope(int index) {
        header("DELETE AN ENVELOPE");
        int envIndex = selectEnvelope(index, "delete");
        if (envIndex == -1) {
            return;
        }

        ArrayList<Envelope> list = vaults.get(index).envelopes;
        if (confirm("\nAre you sure you want to delete \"" + list.get(envIndex).platform + "\"?")) {
            list.remove(envIndex);
            System.out.println("Envelope deleted.");
        } else {
            System.out.println("Deletion cancelled.");
        }
        pause();
    }

    // 4. Edit an envelope
    static void editEnvelope(int index) {
        header("EDIT AN ENVELOPE");
        int envIndex = selectEnvelope(index, "edit");
        if (envIndex == -1) {
            return;
        }

        Envelope env = vaults.get(index).envelopes.get(envIndex);
        header("EDIT: " + env.platform);
        System.out.println(env.displayEnvelope());
        System.out.println("\nWhat do you want to change?\n1. Platform\n2. Website URL\n3. Email"
                + "\n4. Phone number\n5. Password\n0. Cancel");
        int choice = readInt("\nChoose an option:", 0, 5);

        switch (choice) {
            case 1:
                env.platform = readNonEmpty("Enter the new platform name:");
                break;
            case 2:
                env.webUrl = readLine("Enter the new website url (press Enter to clear it):");
                break;
            case 3:
                env.email = readLine("Enter the new email (press Enter to clear it):");
                break;
            case 4:
                env.phoneNum = readLine("Enter the new phone number (press Enter to clear it):");
                break;
            case 5:
                env.password = readPasswordTwice();
                break;
            case 0:
                System.out.println("Edit cancelled.");
                pause();
                return;
        }
        System.out.println("\nEnvelope updated.");
        pause();
    }

    // 5. Search envelopes by platform, email or website
    static void searchEnvelope(int index) {
        header("SEARCH ENVELOPES");
        ArrayList<Envelope> list = vaults.get(index).envelopes;
        if (list.isEmpty()) {
            System.out.println("No envelopes in this vault.");
            pause();
            return;
        }

        String keyword = readNonEmpty("Search by platform name, email or website:").toLowerCase();
        boolean found = false;
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            Envelope e = list.get(i);
            if (e.platform.toLowerCase().contains(keyword)
                    || e.email.toLowerCase().contains(keyword)
                    || e.webUrl.toLowerCase().contains(keyword)) {
                System.out.println((i + 1) + ". " + e.platform);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No envelope found matching \"" + keyword + "\".");
        }
        pause();
    }

    // 6. Open one envelope and show all its details
    static void openEnvelope(int index) {
        header("OPEN AN ENVELOPE");
        int envIndex = selectEnvelope(index, "open");
        if (envIndex == -1) {
            return;
        }

        Envelope env = vaults.get(index).envelopes.get(envIndex);
        header("ENVELOPE: " + env.platform);
        System.out.println(env.displayEnvelope());
        pause();
    }


    //  MAIN
   

    static void mainMenu() {
        boolean running = true;
        while (running) {
            showTitle();
            if (vaults.isEmpty()) {
                System.out.println("Your vault repository is empty. Create a vault to start storing your passwords.\n");
            }
            System.out.println("Main Menu:\n1. Create a new vault\n2. View vaults\n3. Delete vault\n4. Edit vault"
                    + "\n5. Search vault\n6. Open a vault\n7. Exit");
            int choice = readInt("\nChoose an option (1-7):", 1, 7);

            switch (choice) {
                case 1:
                    createVault();
                    break;
                case 2:
                    viewVaults();
                    break;
                case 3:
                    deleteVault();
                    break;
                case 4:
                    editVault();
                    break;
                case 5:
                    searchVault();
                    break;
                case 6:
                    openVault();
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            mainMenu();
        } catch (NoSuchElementException e) {
          
            System.out.println("\nInput was closed. Exiting the program.");
        } finally {
            scanner.close(); 
            System.out.println("Goodbye!");
        }
    }
}