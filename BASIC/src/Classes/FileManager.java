package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

/**
 * The FileManager class provides methods for fetching data from various data files and populating
 * the BASIC application's data structures.
 *
 * It includes methods for fetching host, gold, standard, shared property, and full property data
 * from their respective data files. Additionally, it contains a utility method to retrieve a
 * host by ID.
 *
 * The data files are assumed to be in the current working directory and have specific formats:
 * "host.dat" for host data, "gold.dat" for gold user data, "standard.dat" for standard user data,
 * "sharedProperty.dat" for shared property data, and "fullProperty.dat" for full property data.
 *
 *
 * @author Your Name
 * @version 1.0
 * @see BASIC
 * @see Host
 * @see Gold
 * @see Standard
 * @see SharedProperty
 * @see FullProperty
 */
public class FileManager {

    /**
     * Fetches host data from the "host.dat" file and populates the BASIC application's user list.
     *
     * @param b The BASIC instance to update with fetched data.
     */
    public static void fetchHostData(BASIC b) {
        if (new File("host.dat").exists()) {
            try {
                Scanner sHost = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("host.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];

                if (sHost.hasNext())
                    sHost.nextLine();
                while (sHost.hasNext()) {
                    line = sHost.nextLine();
                    while (!line.contains("Host")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!sHost.hasNext())
                            break;
                        line = sHost.nextLine();
                    }
                    Host host = new Host();
                    host.setUserId(Integer.parseInt(info.get(0)));
                    host.setFirstName(info.get(1));
                    host.setLastName(info.get(2));
                    host.setDateOfBirth(LocalDate.parse(info.get(3)));
                    host.setRegistrationDate(LocalDate.parse(info.get(4)));
                    host.setTaxNumber(Double.parseDouble(info.get(5)));
                    b.users.add(host);
                    info.clear();
                }
                sHost.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Fetches gold user data from the "gold.dat" file and populates the BASIC application's user list.
     *
     * @param b The BASIC instance to update with fetched data.
     */
    public static void fetchGoldData(BASIC b) {
        if (new File("gold.dat").exists()) {
            try {
                Scanner sGold = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("gold.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];

                if (sGold.hasNext())
                    sGold.nextLine();
                while (sGold.hasNext()) {
                    line = sGold.nextLine();
                    while (!line.contains("Gold")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!sGold.hasNext())
                            break;
                        line = sGold.nextLine();
                    }
                    Gold gold = new Gold();
                    gold.setUserId(Integer.parseInt(info.get(0)));
                    gold.setFirstName(info.get(1));
                    gold.setLastName(info.get(2));
                    gold.setDateOfBirth(LocalDate.parse(info.get(3)));
                    gold.setRegistrationDate(LocalDate.parse(info.get(4)));
                    gold.setPreferredPaymentMethod(info.get(5));
                    gold.setGoldLevel(Integer.parseInt(info.get(6)));

                    b.users.add(gold);
                    info.clear();
                }
                sGold.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Fetches standard user data from the "standard.dat" file and populates the BASIC application's user list.
     *
     * @param b The BASIC instance to update with fetched data.
     */
    public static void fetchStandardData(BASIC b) {
        if (new File("standard.dat").exists()) {
            try {
                Scanner sStandard = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("standard.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];

                if (sStandard.hasNext())
                    sStandard.nextLine();
                while (sStandard.hasNext()) {
                    line = sStandard.nextLine();
                    while (!line.contains("Standard")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!sStandard.hasNext())
                            break;
                        line = sStandard.nextLine();
                    }
                    Standard standard = new Standard();
                    standard.setUserId(Integer.parseInt(info.get(0)));
                    standard.setFirstName(info.get(1));
                    standard.setLastName(info.get(2));
                    standard.setDateOfBirth(LocalDate.parse(info.get(3)));
                    standard.setRegistrationDate(LocalDate.parse(info.get(4)));
                    standard.setPreferredPaymentMethod(info.get(5));

                    b.users.add(standard);
                    info.clear();
                }
                sStandard.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Fetches shared property data from the "sharedProperty.dat" file
     * and populates the BASIC application's property list.
     *
     * @param b The BASIC instance to update with fetched data.
     */
    public static void fetchSharedPropertyData(BASIC b) {
        if (new File("sharedProperty.dat").exists()) {
            try {
                Scanner sSharedProperty = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("sharedProperty.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];

                if (sSharedProperty.hasNext())
                    sSharedProperty.nextLine();
                while (sSharedProperty.hasNext()) {
                    line = sSharedProperty.nextLine();
                    while (!line.contains("Shared")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!sSharedProperty.hasNext())
                            break;
                        line = sSharedProperty.nextLine();
                    }
                    SharedProperty sharedProperty = new SharedProperty();
                    sharedProperty.setPropertyId(Integer.parseInt(info.get(0)));
                    sharedProperty.setNoBedRooms(Integer.parseInt(info.get(1)));
                    sharedProperty.setNoRooms(Integer.parseInt(info.get(2)));
                    sharedProperty.setCity(info.get(3));
                    sharedProperty.setPricePerDay(Float.parseFloat(info.get(4)));

                    Host host = getHostById(b, Integer.parseInt(info.get(5)));
                    sharedProperty.setHost(host);

                    HashMap<LocalDate, String> Inspection = new HashMap<>();
                    for (HashMap.Entry<LocalDate, String> entry : sharedProperty.getInspection().entrySet()) {
                        LocalDate date = entry.getKey();
                        String text = entry.getValue();
                        Inspection.put(date, text);
                    }

                    sharedProperty.setInspection(Inspection);

                    b.properties.add(sharedProperty);
                    info.clear();
                }
                sSharedProperty.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Fetches full property data from the "fullProperty.dat" file
     * and populates the BASIC application's property list.
     *
     * @param b The BASIC instance to update with fetched data.
     */
    public static void fetchFullPropertyData(BASIC b) {
        if (new File("fullProperty.dat").exists()) {
            try {
                Scanner sFullProperty = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("fullProperty.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];

                if (sFullProperty.hasNext())
                    sFullProperty.nextLine();
                while (sFullProperty.hasNext()) {
                    line = sFullProperty.nextLine();
                    while (!line.contains("Full")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!sFullProperty.hasNext())
                            break;
                        line = sFullProperty.nextLine();
                    }
                    FullProperty fullProperty = new FullProperty();
                    fullProperty.setPropertyId(Integer.parseInt(info.get(0)));
                    fullProperty.setNoBedRooms(Integer.parseInt(info.get(1)));
                    fullProperty.setNoRooms(Integer.parseInt(info.get(2)));
                    fullProperty.setCity(info.get(3));
                    fullProperty.setPricePerDay(Float.parseFloat(info.get(4)));
                    fullProperty.setPropertySize(Double.parseDouble(info.get(5)));

                    Host host = getHostById(b, Integer.parseInt(info.get(6)));
                    fullProperty.setHost(host);

                    HashMap<LocalDate, String> Inspection = new HashMap<>();
                    for (HashMap.Entry<LocalDate, String> entry : fullProperty.getInspection().entrySet()) {
                        LocalDate date = entry.getKey();
                        String text = entry.getValue();
                        Inspection.put(date, text);
                    }

                    fullProperty.setInspection(Inspection);


                    b.properties.add(fullProperty);
                    info.clear();
                }
                sFullProperty.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Retrieves a host by ID from the BASIC application's user list.
     *
     * @param b      The BASIC instance containing the user list.
     * @param userId The ID of the host to retrieve.
     * @return The Host object with the specified ID, or null if not found.
     */
    public static Host getHostById(BASIC b, int userId) {
        for(User host: b.users) {
            if(host.getUserId() == userId && host instanceof Host)
                return (Host) host;
        }
        return null;
    }
}
