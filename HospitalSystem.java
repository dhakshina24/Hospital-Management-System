import java.util.*;

class Doctor extends Appointments // Doctor
{

    private String Name, Specialization, Qualification;
    private long ID;
    private int Room_no;
    private ArrayList<String> timings = new ArrayList<>();
    public static ArrayList<Doctor> doc = new ArrayList<>();

    public Doctor() {
        this.addDoc();
        System.out.println("Doctor added");
        System.out.println();
    }

    public Doctor(int a) {
        // Only for the purpose of sorting list
    }

    public Doctor(String Name, String Specialization, String Qualification, long ID, int Room_no,
            ArrayList<String> time, int add_timefrom, int add_timeto) {
        // for addition of existing doctors
        this.Name = Name;
        this.Specialization = Specialization.toUpperCase();
        this.Qualification = Qualification.toUpperCase();
        this.ID = ID;
        this.Room_no = Room_no;
        for (int i = add_timefrom; i <= (add_timeto); i++)
            this.timings.add(time.get(i));
        this.createSlots();
    }

    public static void existingDoctor(ArrayList<Doctor> d) {
        // Currently 5 existing Doctors hence
        ArrayList<String> time = new ArrayList<String>();
        time.add("10:00 - 19:00");
        d.add(new Doctor("Dr. Raj Rahul", "ophthalmologist", "MBBS, MD", 208543, 7, time, 0, 0));
        time.add("9:00 - 14:00");
        time.add("15:00 - 20:00");
        d.add(new Doctor("Dr. Gopika Bharadwaj", "ENT", "MBBS, MD", 100476, 22, time, 1, 2));
        time.add("10:00 - 13:30");
        time.add("15:00 - 20:30");
        d.add(new Doctor("Dr. Vikram Lolit", "General Physician", "MBBS, MD", 134870, 4, time, 3, 4));
        time.add("10:00 - 13:30");
        time.add("15:00 - 20:30");
        d.add(new Doctor("Dr. Sarika Vajpai ", "Pediatrician", "MBBS, MD", 231145, 16, time, 5, 6));
        time.add("10:00 - 13:30");
        time.add("15:00 - 17:30");
        time.add("19:00 - 21:00");
        d.add(new Doctor("Dr. Farookh Shah", "Dentist", "BDS, MDS", 182504, 9, time, 7, 9));
    }

    public void DoctorMenu(int y) {
        Scanner sc = new Scanner(System.in);
        // invoke existing doctors file
        if (y == 0)
            Doctor.existingDoctor(doc);
        // Begin Menu if option 1 has been selected
        if (y == 1) {
            while (true) {
                int option = 0;
                System.out.println();
                System.out.println("Choose an option:");
                System.out.println("1.Add Doctor");
                System.out.println("2.View Doctor");
                System.out.println("3.Edit Doctor detail");
                System.out.println("4.Remove Doctor");
                System.out.println("5.Exit to Main Menu");
                option = sc.nextInt();

                switch (option) {
                    case 1: {
                        int n;
                        System.out.println("Enter number of doctors to add");
                        n = sc.nextInt();
                        for (int i = 0; i < n; i++)
                            doc.add(new Doctor());
                        break;
                    }
                    case 2: {
                        this.viewDoctorMenu(doc);
                        break;
                    }

                    case 3: {
                        System.out.println("Enter Doctor details to edit");
                        sc.nextLine();
                        System.out.println("Enter current Name of Doctor");
                        String n = sc.nextLine();
                        System.out.println("Enter ID (0 if remains the same)");
                        long id = sc.nextLong();
                        sc.nextLine();
                        System.out.println("Enter Name ('-' if remains the same)");
                        String name = sc.nextLine();
                        System.out.println("Enter Specialization ('-' if remains the same)");
                        String spez = sc.nextLine().toUpperCase();
                        System.out.println("Enter Qualification separated by commas ('-' if remains the same)");
                        String qual = sc.nextLine().toUpperCase();
                        System.out.println("Enter Room number (0 if remains the same)");
                        int rno = sc.nextInt();
                        sc.nextLine();

                        if (name.equals("-"))
                            name = null;
                        if (spez.equals("-"))
                            spez = null;
                        if (qual.equals("-"))
                            qual = null;

                        Doctor.setDoctor(doc, n, name, spez, qual, id, rno);
                        break;
                    }

                    case 4: {
                        sc.nextLine();
                        System.out.println("Enter Doctor name for deletion");
                        String name = sc.nextLine();
                        System.out.println("Enter Doctor ID for deletion");
                        long id = sc.nextLong();
                        sc.nextLine();
                        Doctor.deleteDoc(name, id, doc);
                        break;
                    }
                    case 5: {
                        return;
                    }
                    default:
                        return;
                }

            }
        }

    }

    private void viewDoctorMenu(ArrayList<Doctor> doc) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose from the following options");
        System.out.println("1.View all Doctors");
        System.out.println("2.View all Doctors in Specialization");
        System.out.println("3.View a particular Doctor");
        int op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1: {
                Doctor.viewDocFormat(doc, 0, 0, null, 0);
                break;
            }
            case 2: {
                Doctor.viewSpecDoc(doc);
                break;
            }
            case 3: {
                System.out.println("Enter Doctor name (if only ID known and name not known, enter '-')");
                String name = sc.nextLine();
                if (name.equals("-"))
                    name = null;
                System.out.println("Enter Doctor ID (if ID not known, enter 0)");
                long id = sc.nextLong();
                sc.nextLine();
                Doctor.viewDocFormat(doc, 0, 0, name, id);
                break;
            }
            default:
                return;
        }
        return;
    }

    private void addDoc() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        this.ID = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter Name");
        this.Name = sc.nextLine();
        System.out.println("Enter Specialization");
        this.Specialization = sc.nextLine().toUpperCase();
        System.out.println("Enter Qualification separated by commas");
        this.Qualification = sc.nextLine().toUpperCase();
        System.out.println("Enter Room number");
        this.Room_no = sc.nextInt();
        sc.nextLine();

        System.out.println("If the timings are broken down with breaks, pls enter number of continuous shifts"
                + "(eg 11:00 am to 1:00 pm and 3:00 pm to 7:00 pm is 2 shifts) ");
        int no = 0;
        no = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < no; i++) {
            String temp;
            System.out.println("Enter Doctor shift timings in format hh:mm - hh:mm  24 hr time");
            temp = sc.nextLine();
            this.timings.add(temp);
        }
        this.createSlots();
    }

    void createSlots() {
        StringTokenizer st;
        for (int i = 0; i < this.timings.size(); i++) {
            st = new StringTokenizer(this.timings.get(i), ": -");
            int hh, mm, nh, nm;
            hh = Integer.parseInt(st.nextToken()); // starting time hr
            mm = Integer.parseInt(st.nextToken()); // starting time min
            nh = Integer.parseInt(st.nextToken()); // to time hr
            nm = Integer.parseInt(st.nextToken()); // to time min
            while (hh < nh || ((hh == nh) && mm <= nm)) {
                this.slot_timings.add(String.format("%02d:%02d", hh, mm));
                this.booked.add(false); // i.e slot not booked
                this.patient.add(null); // no patient has booked the slot
                if (mm + 30 < 60) {
                    mm += 30;
                } else {
                    hh += 1;
                    mm = mm + 30 - 60;
                }
            }
        }
    }

    protected static void setDoctor(ArrayList<Doctor> d, String n, String Name, String Specialization,
            String Qualification, long ID, int Room_no) {
        // to edit doctor info
        for (Doctor dr : d) {
            if (n.equals(dr.Name)) {
                if (Name != null)
                    dr.Name = Name;
                if (Specialization != null)
                    dr.Specialization = Specialization;
                if (Qualification != null)
                    dr.Qualification = Qualification;
                if (ID != 0)
                    dr.ID = ID;
                if (Room_no != 0)
                    dr.Room_no = Room_no;
                System.out.println("Modified");
            }
        }

    }

    protected static void viewDocFormat(ArrayList<Doctor> d, int x, int y, String dname, long id) {
        Scanner sc = new Scanner(System.in);

        if (dname == null && id == 0) {
            System.out.println(
                    "Enter 1 to view in ascending order of Names or Enter 2 to view in ascending order of IDs");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println("Doctors list");
            System.out.println();
            System.out.print("ID");
            System.out.print("\t\tName");
            System.out.print("\t\t\tSpecialization");
            System.out.print("\t\t\tQualification");
            System.out.print("\t\tRoom number");
            System.out.println();
            if (x != 0 || y != 0) {
                // currently in ascending order of specialization
                // clone arrayList
                List<Doctor> d_temp = new ArrayList<>();
                d_temp = d.subList(x, (y + 1));
                if (option == 1) {
                    Collections.sort(d_temp, new Doctor(0).new NameComparator());
                } else if (option == 2) {
                    Collections.sort(d_temp, new Doctor(0).new IDComparator());
                }
                for (int i = 0; i < d_temp.size(); i++)
                    d_temp.get(i).viewAllDocs();
            }
            // to view specific doctor
            else {
                if (option == 1) {
                    Collections.sort(d, new Doctor(0).new NameComparator());
                } else if (option == 2) {
                    Collections.sort(d, new Doctor(0).new IDComparator());
                }
                for (int i = 0; i < d.size(); i++)
                    d.get(i).viewAllDocs();
            }
        } else {
            for (int i = 0; i < d.size(); i++) {
                if (((dname == null) && d.get(i).ID == id)
                        || (d.get(i).Name.compareToIgnoreCase(dname) == 0 || d.get(i).ID == id)) {
                    System.out.println("Doctors list");
                    System.out.println();
                    System.out.print("ID");
                    System.out.print("\t\tName");
                    System.out.print("\t\t\tSpecialization");
                    System.out.print("\t\t\tQualification");
                    System.out.print("\t\tRoom number");
                    System.out.println();
                    d.get(i).viewAllDocs();
                    break;
                } else
                    System.out.println("Error");
            }
        }
    }

    protected static void viewSpecDoc(ArrayList<Doctor> d) {
        Scanner sc = new Scanner(System.in);

        Collections.sort(d, new Doctor(0).new SpecializationComparator());
        // sc.nextLine();
        System.out.println("Enter the Specialization:");
        String Sp = sc.nextLine();
        int x = 0, y = 0, flag = 0;
        for (int i = 0; i < d.size() - 1; i++) {
            if (d.get(i).Specialization.compareToIgnoreCase(Sp.toUpperCase()) == 0) {
                if (x == 0) {
                    x = i;
                }
                if (d.get(i + 1).Specialization.compareToIgnoreCase(Sp.toUpperCase()) != 0) {
                    y = i;
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0)
            System.out.println("No Doctor currently in the existing specialization, showing all Doctors");
        viewDocFormat(d, x, y, null, 0);
    }

    private void viewAllDocs() {
        System.out.println();
        System.out.print(this.ID);
        System.out.print("\t" + this.Name);
        for (int i = 0; i < (28 - this.Name.length()); i++)
            System.out.print(" ");
        System.out.print("\t" + this.Specialization);
        for (int i = 0; i < (32 - this.Specialization.length()); i++)
            System.out.print(" ");
        System.out.print(this.Qualification);
        for (int i = 0; i < (30 - this.Qualification.length()); i++)
            System.out.print(" ");
        System.out.println(this.Room_no);
        System.out.println("Free slots:");
        this.freeSlots();
        System.out.println();
        System.out.println("Booked slots:");
        this.bookedSlots();
    }

    void freeSlots() {
        for (int i = 0; i < this.slot_timings.size(); i++) {
            if (!this.booked.get(i)) {
                // print slot timing for doctor
                System.out.print(this.slot_timings.get(i) + "\t");
            }
        }
    }

    void bookedSlots() {
        for (int i = 0; i < this.slot_timings.size(); i++) {
            if (this.booked.get(i)) {
                // print slot timing for doctor
                System.out.print(this.slot_timings.get(i) + "\t");
                // print patient name
                System.out.println(this.patient.get(i));
            }
        }
    }

    String callApp(String docname, String pname) {
        String returned = Doctor.docApp(docname, doc, pname);
        return returned; // returns concat docname and slot timing
    }

    static String docApp(String docname, ArrayList<Doctor> d, String pname) {
        Scanner sc = new Scanner(System.in);
        String returned = null;
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).Name.compareToIgnoreCase(docname) == 0) {
                System.out.println("Enter 1 to book/change appointment with Doctor");
                System.out.println("Enter 2 to cancel appointment/make it walk-in with Doctor");
                System.out.println("Enter 3 to Exit");
                int option = sc.nextInt();
                sc.nextLine();
                if (option == 1) {
                    returned = d.get(i).bookSlot(pname, i, d);
                    break;
                } else if (option == 2) {
                    returned = d.get(i).editSlot(pname, i, d);
                } else {
                    System.out.println("Exit");
                }
            }
        }
        return returned;
    }

    String bookSlot(String pname, int j, ArrayList<Doctor> d) {
        Scanner sc = new Scanner(System.in);
        d.get(j).freeSlots();
        System.out.println("Enter Slot timing in format hh:mm (24 hr) to book, enter 0 to avoid booking");
        String slot = sc.next();
        int hh, mm, i;
        StringTokenizer st = new StringTokenizer(slot, ":");
        hh = Integer.parseInt(st.nextToken());
        mm = Integer.parseInt(st.nextToken());
        i = this.slot_timings.indexOf(String.format("%02d:%02d", hh, mm));

        if (i != -1) {
            this.booked.set(i, true);
            this.patient.set(i, pname);
            System.out.println("Slot booked");
            return (d.get(j).Name + " ").concat(String.format("%02d:%02d", hh, mm)); // returns docname and booked slot
                                                                                     // timing as a concat string
        } else {
            System.out.println("Incorrect slot, slot not found");
            return null;
        }
    }

    String editSlot(String pname, int j, ArrayList<Doctor> d) {
        // cancelling appointment and freeing slot
        this.booked.set(j, false);
        this.patient.set(j, null);
        System.out.println("Fixed Appointment Cancelled.");
        return "1 " + this.slot_timings.get(j);
    }

    static void deleteDoc(String dname, long id, ArrayList<Doctor> d) {
        int flag = 0;
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).Name.compareToIgnoreCase(dname) == 0 && d.get(i).ID == id) {
                d.get(i).slot_timings.clear();
                d.get(i).booked.clear();
                d.get(i).patient.clear();
                d.remove(i);
                System.out.println("Doctor data Removed");
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            System.out.println("Doctor not Found");
    }

    class IDComparator implements Comparator<Doctor> {
        public int compare(Doctor d1, Doctor d2) {
            if (d1.ID > d2.ID) {
                return 1;
            } else if (d1.ID == d2.ID) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    class NameComparator implements Comparator<Doctor> {
        public int compare(Doctor d1, Doctor d2) {
            return d1.Name.compareToIgnoreCase(d2.Name);
        }
    }

    class SpecializationComparator implements Comparator<Doctor> {
        public int compare(Doctor d1, Doctor d2) {
            return d1.Specialization.compareToIgnoreCase(d2.Specialization);
        }
    }
}

abstract class Slots {
    // slot is personal to each doctor
    ArrayList<String> slot_timings = new ArrayList<>();
    ArrayList<Boolean> booked = new ArrayList<>();
    ArrayList<String> patient = new ArrayList<>();

    void createSlots() {
    }

    void freeSlots() {
    }

    void bookedSlots() {
    }
}

abstract class Appointments extends Slots {
    String bookSlot(String pname, int i, ArrayList<Doctor> d) {
        return null;
    }

    String editSlot(String pname, int i, ArrayList<Doctor> d) {
        return null;
    }
}

class Facility // Facility
{
    String fname;
    ArrayList<String> flist;

    Facility() {
        flist = new ArrayList<String>();
        flist.add("Ambulance");
        flist.add("Emergency");
        flist.add("Cafeteria");
        flist.add("Exit and return to Main menu");
    }

    Scanner in = new Scanner(System.in);

    void fmenu() {
        int op;
        while (true) {
            System.out.println("HOSPITAL FACILITIES");
            System.out.println("1.Add New Facilities");
            System.out.println("2.View Existing list of Facilities");
            System.out.println("3.Remove Facilities");
            System.out.println("4.Exit");
            System.out.println("Enter Option");
            op = in.nextInt();
            switch (op) {
                case 1:
                    addfname();
                    break;
                case 2:
                    viewflist();
                    break;
                case 3:
                    removefname();
                    break;
                case 4:
                    System.exit(0);
            }

        }
    }

    void addfname() {
        System.out.println("Enter Facility name:");
        flist.add(in.next());
    }

    void removefname() {
        System.out.println("Enter Facility name to be removed:");
        flist.remove(in.next());

    }

    class Ambulance {
        ArrayList<String> patientlist;// should be added to main list of patients
        String src, dest;
        ArrayList<String> paramed;// Paramedics
        final int totambo;// Total no of ambulances
        int noambo;// Available ambulances
        Category c;

        Ambulance() {
            patientlist = new ArrayList<String>();
            in.nextLine();
            System.out.println("Enter destination:");
            dest = in.nextLine();
            in.nextLine();
            System.out.println("Enter current location:");
            src = in.nextLine();
            paramed = new ArrayList<String>();
            System.out.println("Enter total no of ambulances in hospital:");
            totambo = in.nextInt();
            System.out.println("Enter response");
            in.nextLine();
            String r = in.nextLine();
            c = new Category(r);
        }

        class Category {
            int no; // Category no
            String response;// for 1.Life Threatening 2.Serious Condition 3.Urgent

            Category(String r) {
                response = r;
                if (response.equals("Life Threatening"))
                    no = 1;
                else if (response.equals("Serious Condition"))
                    no = 2;
                else if (response.equals("Urgent"))
                    no = 3;
            }
        }

        public void ambomenu() {
            int op;
            while (true) {
                System.out.println("Modifications to be done in Ambulance:");
                System.out.println("1.Change number of ambulances available");
                System.out.println("2.Display total no of ambulances available");
                System.out.println("3.Add Paramedics");
                System.out.println("4.Remove Paramedics");
                System.out.println("5.Display List of Paramedics");
                System.out.println("6.Add Patients");
                System.out.println("7.Display List of Patients");
                System.out.println("8.Display Details of Ambulance");
                System.out.println("9.Back to List of Facilities");
                System.out.println("10.Back to Main Menu");
                System.out.println("Enter Option");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        changeambos();
                        break;
                    case 2:
                        availableambos();
                        break;
                    case 3:
                        addparamed();
                        break;
                    case 4:
                        removeparamed();
                        break;
                    case 5:
                        dispparamed();
                        break;
                    case 6:
                        addpatient();
                        break;
                    case 7:
                        disppatients();
                        break;
                    case 8:
                        dispdetails();
                        break;
                    case 9:
                        viewflist();
                        break;
                    case 10:
                        fmenu();
                        break;
                }
            }

        }

        void changeambos() // To make changes
        {
            System.out.println("1.Enter no of ambulances available");
            System.out.println("2.Enter No of occupied ambulances:");
            System.out.println("Enter option:");
            switch (in.nextInt()) {
                case 1:
                    int changenoa = in.nextInt();
                    if (changenoa + noambo <= totambo)
                        noambo += changenoa;
                    else
                        System.out.println("Exceeding total no of ambulances");
                    break;
                case 2:
                    int changenos = in.nextInt();
                    if (noambo - changenos > 0 && noambo - changenos <= totambo)
                        noambo -= changenos;
                    else
                        System.out.println("No ambulances available");
                    break;
            }
        }

        void availableambos() {
            System.out.println("No of available ambulances:" + noambo);
        }

        void addparamed() {
            System.out.println("Enter no of paramedics to add:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter paramedic name:");
                String na = in.next();
                paramed.add(na);
                no--;
            }
        }

        void removeparamed() {
            System.out.println("Enter no of paramedics to remove:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter paramedic name:");
                paramed.remove(in.next());
                no--;
            }
        }

        void dispparamed() {
            System.out.println("List of paramedics:");
            int n = 1;
            for (String p : paramed) {
                System.out.println(n + "  " + p + "\n");
                n++;
            }
        }

        void addpatient() {
            System.out.println("Enter no of patients to add:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter patient name:");
                patientlist.add(in.next());
                no--;
            }
        }

        void disppatients() {
            System.out.println("List of patients in Ambulance:");
            int n = 1;
            for (String p : patientlist)
                System.out.println((n++) + "  " + p + "\n");
        }

        void dispdetails() {
            System.out.println("Ambulance Details:");
            System.out.println("Current location of Ambulance:" + src);
            System.out.println("Destination:" + dest);
            dispparamed();
            System.out.println("Response:" + c.response);
            System.out.println("Category no:" + c.no);
            disppatients();
        }
    }

    class Emergency {
        String triage; // It is a system in emergency med to categorize patients according to severity
                       // of illness
        ArrayList<String> epatient;// Patients that come to Emergency room
        ArrayList<String> edoc;// Doctors on call for Emergency Department

        Emergency() {
            System.out.println("Enter Triage Tag Colour: R/O/Y/G");
            // comments or print it
            // Red-Resuscitation
            // Orange-Urgent
            // Yellow-Less Urgent
            // Green-Not Urgent
            triage = in.next();
            epatient = new ArrayList<String>();
            edoc = new ArrayList<String>();
        }

        public void emergencymenu() {
            int op;
            while (true) {
                System.out.println("Modifications to be done in Emergency:");
                System.out.println("1.Add Doctors");
                System.out.println("2.Remove Doctors");
                System.out.println("3.Display List of Doctors");
                System.out.println("4.Add Patients");
                System.out.println("5.Remove Patients");
                System.out.println("6.Display List of Patients");
                System.out.println("7.Display Details of Emergency Department");
                System.out.println("8.Back to List of Facilities");
                System.out.println("9.Back to Main Menu");
                System.out.println("Enter Option");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        addedoc();
                        break;
                    case 2:
                        removeedoc();
                        break;
                    case 3:
                        dispedoc();
                        break;
                    case 4:
                        addepatient();
                        break;
                    case 5:
                        removeepatient();
                        break;
                    case 6:
                        dispepatient();
                        break;
                    case 7:
                        dispedetails();
                        break;
                    case 8:
                        viewflist();
                        break;
                    case 9:
                        fmenu();
                        break;
                }
            }

        }

        void addedoc() // Add to main list also
        {
            System.out.println("Enter no of doctors to add:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter Doctor name:");
                edoc.add(in.next());
                no--;
            }
        }

        void removeedoc() // Remove from main list also
        {
            System.out.println("Enter no of Doctors to remove:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter Doctor name:");
                edoc.remove(in.next());
                no--;
            }
        }

        void dispedoc() {
            System.out.println("List of Emergency Doctors:");
            int n = 1;
            for (String ed : edoc) {
                System.out.println(n + "  " + ed);
                n++;
            }
        }

        void addepatient() // Have to add to main list also
        {
            System.out.println("Enter no of patients to add:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter patient name:");
                String na = in.next();
                epatient.add(na);
                no--;
            }
        }

        void removeepatient() // Have to remove in main list also
        {
            System.out.println("Enter no of patients to remove:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter patient name:");
                epatient.remove(in.next());
                no--;
            }
        }

        void dispepatient() {
            System.out.println("List of patients:");
            int n = 1;
            for (String ed : epatient)
                System.out.print((n++) + "  " + ed + "\n");
        }

        // This is to display all details of ed but if individual patient is considered
        // then no need of list
        void dispedetails() {
            System.out.println("Details of Emergency Department:");
            System.out.println("Triage:" + triage);
            dispepatient();
            dispedoc();
        }
    }

    class Cafeteria {
        ArrayList<Item> Snacks;
        ArrayList<Item> Drinks;

        class Item {
            String na;
            int stock;
            double cost;

            Item(String n, int s, double c) {
                na = n;
                stock = s;
                cost = c;

            }

            Item(Item i) {
                this.na = i.na;
                this.stock = i.stock;
                this.cost = i.cost;
            }

            Item() {

            }

        }

        Cafeteria() {
            Snacks = new ArrayList<Item>();
            Drinks = new ArrayList<Item>();
            Item d1 = new Item("Water", 500, 1.0);
            Item d2 = new Item("Coffee", 300, 2.0);
            Drinks.add(d1);
            Drinks.add(d2);
            Item s1 = new Item("Cheese Sandwich", 200, 3.0);
            Item s2 = new Item("Salad", 100, 5.0);
            Snacks.add(s1);
            Snacks.add(s2);
        }

        public void cmenu() {
            int op;
            while (true) {
                System.out.println("Modifications to be done to Cafeteria:");
                System.out.println("1.Add Drinks");
                System.out.println("2.Buy Drinks");
                System.out.println("3.Remove Drinks");
                System.out.println("4.Add Snacks");
                System.out.println("5.Buy Snacks");
                System.out.println("6.Remove Snacks");
                System.out.println("7.Display List of Snacks and Drinks");
                System.out.println("8.Back to List of Facilities");
                System.out.println("9.Back to Main Menu");
                System.out.println("Enter Option");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        addDrinks();
                        break;
                    case 2:
                        BuyDrink();
                        break;
                    case 3:
                        removeDrink();
                        break;
                    case 4:
                        addSnack();
                        break;
                    case 5:
                        BuySnack();
                        break;
                    case 6:
                        removeSnack();
                        break;
                    case 7:
                        dispList();
                        break;
                    case 8:
                        viewflist();
                        break;
                    case 9:
                        fmenu();
                        break;
                }
            }

        }

        void addDrinks() {
            System.out.println("Enter no of Drinks to add");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter name of Drink,stock and cost");
                Item d = new Item(in.next(), in.nextInt(), in.nextDouble());
                Drinks.add(d);
                no--;
            }

        }

        void BuyDrink() {
            System.out.println("Name and quantity of Drink to buy:");
            String sna = in.next();
            int no = in.nextInt();
            for (Item i : Drinks) {
                if ((i.na).equals(sna)) {
                    if (i.stock >= no)
                        i.stock = i.stock - no;
                    else
                        System.out.println("Out of Stock");
                }

            }
        }

        void removeDrink() {
            System.out.println("Enter no of Drinks to remove:");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter name of Drink to be removed:");
                String na = in.next();
                for (Item i : Drinks) {
                    if ((i.na).equals(na))
                        Drinks.remove(i);
                }
                no--;
            }
        }

        void addSnack() {
            System.out.println("Enter no of Snacks to add");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter name of snack,stock and cost");
                Item s = new Item(in.next(), in.nextInt(), in.nextDouble());
                Snacks.add(s);
                no--;
            }
        }

        void BuySnack() {
            System.out.println("Name and quantity of snack to buy:");
            String sna = in.next();
            int no = in.nextInt();
            for (Item i : Snacks) {
                if ((i.na).equals(sna)) {
                    if (i.stock >= no)
                        i.stock = i.stock - no;
                    else
                        System.out.println("Out of Stock");
                }

            }

        }

        void removeSnack() {
            System.out.println("Enter no of Snacks to be removed");
            int no = in.nextInt();
            while (no > 0) {
                System.out.println("Enter name of snack to be removed:");
                String na = in.next();
                for (Item i : Snacks) {
                    if ((i.na).equals(na))
                        Snacks.remove(i);
                }
                no--;
            }

        }

        void dispList() {
            int nd = 1, ns = 1;
            System.out.println("List of Snacks:");
            for (Item i : Snacks) {
                System.out.println(
                        ns + " " + "Name:" + i.na + "\n" + "Stock:" + i.stock + "\n" + "Cost:" + i.cost + "\n");
                ns++;
            }

            System.out.println("List of Drinks:");
            for (Item i : Drinks) {
                System.out.println(
                        nd + " " + "Name:" + i.na + "\n" + "Stock:" + i.stock + "\n" + "Cost:" + i.cost + "\n");
                nd++;
            }
        }
    }

    void viewflist() {
        int op;
        while (true) {
            int n = 1;
            System.out.println("LIST OF FACILITIES");
            for (String s : flist) {
                System.out.println(n + "." + "  " + s);
                n++;
            }
            System.out.println("Enter Option");
            op = in.nextInt();
            switch (op) {
                case 1:
                    Ambulance a = new Ambulance();
                    a.ambomenu();
                    break;
                case 2:
                    Emergency e = new Emergency();
                    e.emergencymenu();
                    break;
                case 3:
                    Cafeteria c = new Cafeteria();
                    c.cmenu();
                    break;
                case 4:
                    fmenu();
                    break;
            }

        }
    }

}

// MODIFIED PATIENT CLASS
class Patient {
    public static ArrayList<Details> plist;

    class Details {
        String na, diagnosis, timing, drna;
        long phoneno;

        Details(String n, long phno, String diag, String time, String dr, int i) {
            na = n;
            phoneno = phno;
            diagnosis = diag;
            if (i != 0) {
                Doctor dApp = new Doctor(0);
                String s = dApp.callApp(dr, n);
                StringTokenizer st;
                if (s != null) {
                    if (s.charAt(0) == '1') {
                        st = new StringTokenizer(s, " ");
                        st.nextToken(); // pointing to "1"
                        // appointment cancelled, remove the appointment time from patient
                        timing = null;
                    } else {
                        st = new StringTokenizer(s, " ");
                        // add doctor, add diagnosis, add timing
                        drna = st.nextToken() + " " + st.nextToken() + " " + st.nextToken(); // since Dr., first name,
                                                                                             // last name
                        timing = st.nextToken();
                    }
                }
            } else {
                drna = dr;
                timing = time;
            }
        }

        Details(String n) {
            na = n;
            phoneno = 0;
            diagnosis = " ";
            timing = " ";
            drna = " ";
        }

        Details() {

        }
    }

    Patient(String n, long p, String diag, String time, String dr) {
        Details d = new Details(n, p, diag, time, dr, 1);
        plist = new ArrayList<>();
        plist.add(new Details("Jyothi", 368973645, "Ear pain", null, "Dr. Gopika Bharadwaj", 0));
        plist.add(new Details("Joseph", 732463214, "Fever", null, "Dr. Vikram Lolit", 0));
        plist.add(d);
    }

    Patient(String name) {
        Details d = new Details(name);
        plist = new ArrayList<>();
        plist.add(new Details("Jyothi", 368973645, "Ear pain", null, "Dr. Gopika Bharadwaj", 0));
        plist.add(new Details("Joseph", 732463214, "Fever", null, "Dr. Vikram Lolit", 0));
        plist.add(d);
    }

    Patient() {
        plist = new ArrayList<>();
        plist.add(new Details("Jyothi", 368973645, "Ear pain", null, "Dr. Gopika Bharadwaj", 0));
        plist.add(new Details("Joseph", 732463214, "Fever", null, "Dr. Vikram Lolit", 0));
    }

    Scanner in = new Scanner(System.in);

    void addPatients() {
        System.out.println("Enter no of Patients to add");
        int no = in.nextInt();
        in.nextLine();
        while (no > 0) {
            String n, dg, dr;
            long p;
            System.out.println("Enter Patient name:");
            n = in.nextLine();
            System.out.println("Enter Phone no:");
            p = in.nextLong();
            in.nextLine();
            System.out.println("Enter Diagnosis.");
            dg = in.nextLine();
            System.out.println("Enter Doctor name.");
            dr = in.nextLine();
            Details d = new Details(n, p, dg, null, dr, 1);
            plist.add(d);
            no--;
        }

    }

    void removePatient() {
        System.out.println("Enter no of Patients to remove:");
        int no = in.nextInt();
        in.nextLine();
        Details det = new Details();
        int i;
        while (no > 0) {
            System.out.println("Enter name of Patient to be removed:");
            String na = in.nextLine();
            for (i = 0; i < plist.size(); i++) {
                det = plist.get(i);
                if ((det.na).equalsIgnoreCase(na))
                    plist.remove(det);
            }
            no--;
        }
    }

    void modifyna() {
        System.out.println("Enter no of Patients Name to Modify:");
        int no = in.nextInt();
        in.nextLine();
        Details det = new Details();
        int i;
        while (no > 0) {
            System.out.println("Enter name of Patient to be modified and modified name:");
            String na = in.nextLine();
            String modna = in.nextLine();
            for (i = 0; i < plist.size(); i++) {
                det = plist.get(i);
                if ((det.na).equalsIgnoreCase(na)) {
                    det.na = modna;
                    plist.set(i, det);
                }
            }
            no--;
        }

    }

    void modifyphoneno() {
        System.out.println("Enter no of Patients Phone nos to Modify:");
        int no = in.nextInt();
        in.nextLine();
        Details det = new Details();
        int i;
        while (no > 0) {
            System.out.println("Enter Phone no of Patient to be modified and modified Phone no:");
            long ph = in.nextLong();
            long modph = in.nextLong();
            for (i = 0; i < plist.size(); i++) {
                det = plist.get(i);
                if (det.phoneno == ph) {
                    det.phoneno = modph;
                    plist.set(i, det);
                }
            }
            no--;
        }

    }

    void modifydiagnosis() {
        System.out.println("Enter no of Patients Diagnosis to Modify:");
        int no = in.nextInt();
        in.nextLine();
        Details det = new Details();
        int i;
        while (no > 0) {
            System.out.println("Enter Name,Diagnosis of Patient to be modified and modified Diagnosis:");
            String name = in.nextLine();
            String diag = in.nextLine();
            String moddiag = in.nextLine();
            for (i = 0; i < plist.size(); i++) {
                det = plist.get(i);
                if ((det.na).equalsIgnoreCase(name) && (det.diagnosis).equalsIgnoreCase(diag)) {
                    det.diagnosis = moddiag;
                    plist.set(i, det);
                }
            }
            no--;
        }

    }

    void modifyAppt() {
        Details det = new Details();
        in.nextLine();
        int i;
        System.out.println("Enter Name of Patient for Appointment:");
        String name = in.nextLine();
        for (i = 0; i < plist.size(); i++) {
            det = plist.get(i);
            if ((det.na).equalsIgnoreCase(name)) {
                System.out.println("Enter current Dr Name");
                String dr = in.nextLine();
                System.out.println("Enter Reason/Diagnosis");
                String diag = in.nextLine();
                Doctor dApp = new Doctor(0);
                String s = dApp.callApp(dr, det.na);
                StringTokenizer st;
                if (s != null) {
                    if (s.charAt(0) == '1') {
                        st = new StringTokenizer(s, " ");
                        st.nextToken(); // pointing to "1"
                        // appointment cancelled, remove the appointment time from patient
                        det.timing = null;
                    } else {
                        st = new StringTokenizer(s, " ");
                        // add doctor, add diagnosis, add timing
                        det.drna = st.nextToken() + " " + st.nextToken() + " " + st.nextToken();
                        det.timing = st.nextToken();
                        det.diagnosis = diag;
                    }
                }

                plist.set(i, det);
                break; // break added since appointment of only 1 patient
            }
        }
    }

    void dispList() {
        System.out.println("List of Patients:");
        for (Details i : plist) {
            System.out.println("Name: " + i.na + "\n" + "Phone no: " + i.phoneno + "\n" + "Diagnosis: " + i.diagnosis
                    + "\n" + "Timing: " + i.timing + "\n" + "Doctor Name: " + i.drna + "\n");
            System.out.println();
        }

    }

    public void PatientMenu(int y) {
        System.out.println();
        int op;
        if (y == 1) {
            while (true) {
                System.out.println();
                System.out.println("Modifications to be done to Patient List:");
                System.out.println("1.Add Patients");
                System.out.println("2.Remove Patients");
                System.out.println("3.Modify Patient Name");
                System.out.println("4.Modify Patient Phone no");
                System.out.println("5.Modify Patient Diagnosis");
                System.out.println("6.Modify existing Patient Appointment");
                System.out.println("7.Display Patients");
                System.out.println("8.Exit");
                System.out.println("Enter Option");
                op = in.nextInt();
                System.out.println();
                switch (op) {
                    case 1:
                        addPatients();
                        break;
                    case 2:
                        removePatient();
                        break;
                    case 3:
                        modifyna();
                        break;
                    case 4:
                        modifyphoneno();
                        break;
                    case 5:
                        modifydiagnosis();
                        break;
                    case 6:
                        modifyAppt();
                        break;
                    case 7:
                        dispList();
                        break;
                    case 8:
                        return;

                }
            }
        }
    }
}

// MEDICINE
class Medical {
    public String med_name, med_comp;
    public int med_cost, quantity;
    ArrayList<Medical> med = new ArrayList<Medical>();

    Medical() {
        this.med_name = null;
        this.med_comp = null;
        this.med_cost = 0;
        this.quantity = 0;
    }

    Medical(String med_name, String med_comp, int med_cost, int quantity) {
        this.med_name = med_name;
        this.med_comp = med_comp;
        this.med_cost = med_cost;
        this.quantity = quantity;
    }

    static void existingmedicines(ArrayList<Medical> med) {
        med.add(new Medical("Panadol", "Paracetamol", 20, 300));
        med.add(new Medical("Brufen", "Ibuprofen", 30, 300));
        med.add(new Medical("Allegra", "Cetirizine", 20, 500));
        med.add(new Medical("Tazloc", "Telmisartan", 30, 500));

    }

    void new_medi() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name of the medicine:");
        med_name = input.nextLine();
        System.out.println("Components of the medicine:");
        med_comp = input.nextLine();
        System.out.println("Cost of the medicine:");
        med_cost = input.nextInt();
        System.out.println("Quantity:");
        quantity = input.nextInt();
    }

    public void display() {

        System.out.println("Name of the medicine: " + med_name);
        System.out.println("Components of the medicine: " + med_comp);
        System.out.println("Cost of the medicine: " + med_cost);
        System.out.println();

    }

    public void removemed() {
        System.out.println("Enter the component of the medicine to be removed:");
        Scanner s = new Scanner(System.in);
        String cn = s.nextLine();

        for (int c = 0; c < med.size(); c++) {
            if (med.get(c).med_comp.equalsIgnoreCase(cn)) {
                med.remove(c);
                System.out.println("Removed");
            }
        }

        System.out.println("The updated list of medicines:");
        for (Medical md : med)
            md.display();
    }

    void info(String mn) {
        int flag = 0;
        Medical medn = new Medical();
        Iterator<Medical> it = med.iterator();
        while (it.hasNext()) {
            medn = it.next();
            if (medn.med_name.equals(mn)) {
                flag = 1;
                medn.display();
                break;
            }

        }
        if (flag != 1)
            System.out.println("Medicine not found");
    }

    public void medicalmenu() {

        int i, ch;
        String mn;
        Scanner in = new Scanner(System.in);
        Medical mi = new Medical();
        Medical.existingmedicines(mi.med);
        System.out.println("How many medicines you want to add to the list");
        int n = in.nextInt();

        for (i = 0; i < n; i++) {
            Medical m = new Medical();
            m.new_medi();
            mi.med.add(m);
        }

        do {
            System.out.println();
            System.out.println("1.Add more files");
            System.out.println("2.Display the information of all the medicines");
            System.out.println("3.Display the information of a particular medicine");
            System.out.println("4.Remove a file");
            System.out.println("5.exit");

            System.out.println("Enter your choice:");
            ch = in.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter the details");
                    Medical m = new Medical();
                    m.new_medi();
                    mi.med.add(m);
                    break;

                case 2:
                    System.out.println("Information of all the medicines");
                    for (Medical md : mi.med)
                        md.display();
                    break;

                case 3:
                    System.out.println("Enter the name of the medicine:");
                    Scanner sc = new Scanner(System.in);
                    mn = sc.nextLine();
                    mi.info(mn);
                    break;

                case 4:
                    mi.removemed();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Wrong choice");

            }

        } while (ch != 5);
    }
}

class Lab extends Patient {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> test;
    ArrayList<ArrayList<String>> ptest;

    Lab() {
        super();
        test = new ArrayList<>();
        test.add("Biochemistry");
        test.add("Blood Test");
        test.add("Urine Test");
        test.add("Colonscopy Test");
        test.add("Gastroscopy Test");
        test.add("Sonoscopy Test");
        test.add("X-Ray");
        test.add("Stool Test");
        test.add("CT scan");
        test.add("Fluoroscopy");
        test.add("MRI");
        test.add("MRA");
        test.add("Mammography");
        test.add("Nuclear medicine");
        test.add("PET imaging");
        test.add("Ultrasound");

        ptest = new ArrayList<ArrayList<String>>();
        ptest.add(0, test);
        ptest.add(1, test);

    }

    void addTests() {
        System.out.println("Enter no of tests to be added");
        int no = sc.nextInt();
        sc.nextLine();
        while (no > 0) {
            System.out.println("Enter Test name:");
            String na = sc.nextLine();
            test.add(na);
            no--;
        }

    }

    void removeTests() {
        System.out.println("Enter no of tests to be added");
        int no = sc.nextInt();
        sc.nextLine();
        while (no > 0) {
            System.out.println("Enter Test name:");
            String na = sc.nextLine();
            test.remove(na);
            no--;
        }

    }

    void ModifyTests() {
        System.out.println("Enter no of tests to be modified");
        int no = sc.nextInt();
        sc.nextLine();
        while (no > 0) {
            System.out.println("Enter Test name to be modified and modified test name:");
            String na = sc.nextLine();
            String modna = sc.nextLine();
            String name;
            for (int i = 0; i < test.size(); i++) {
                name = test.get(i);
                if (name.equals(na)) {
                    name = modna;
                    test.set(i, modna);
                }
            }
            no--;
        }

    }

    void displaytests() {
        System.out.println("Total Tests:");
        int i = 0;
        for (String d : test)
            System.out.print((i++) + " " + d + "\n");
    }

    void addPatientTests() {
        System.out.println("Enter patient phone no and no of tests to be added");
        long ph = sc.nextLong();
        int no = sc.nextInt();
        sc.nextLine();
        Details d = new Details();
        ArrayList<String> t = new ArrayList<String>();
        for (int i = 2; i < plist.size(); i++) {

            d = plist.get(i);
            if (d.phoneno == ph) {

                for (int j = 0; j < no; j++) {
                    System.out.print("Enter Test name:");
                    String tname = sc.nextLine();
                    t.add(j, tname);
                }
            }
            ptest.add(i, t);
        }
    }

    void removePatientTests() {
        System.out.println("Enter patient phone no and no of tests to be removed");
        long ph = sc.nextLong();
        int no = sc.nextInt();
        sc.nextLine();
        Details d = new Details();
        for (int i = 0; i < plist.size(); i++) {
            d = plist.get(i);
            if (d.phoneno == ph) {
                for (int z = 0; z < ptest.get(i).size(); z++) {
                    while (no > 0) {
                        System.out.println("Enter Test name to be removed:");
                        String tname = sc.nextLine();
                        if ((ptest.get(i).get(z)).equals(tname))
                            ptest.get(i).remove(tname);
                        no--;
                    }

                }

            }
        }
    }

    void displayPatientTests() {
        System.out.println("List of Patients with Tests:");
        Details d = new Details();
        for (int i = 0; i < ptest.size(); i++) {
            d = plist.get(i);
            System.out.println("Name:" + d.na);
            for (int j = 0; j < ptest.get(i).size(); j++) {
                System.out.print(ptest.get(i).get(j) + "\n");
            }
            System.out.println();
        }
    }

    public void LabMenu() {
        int op;
        while (true) {
            System.out.println();
            System.out.println("Modifications to be done to Patient List:");
            System.out.println("1.Add Tests");
            System.out.println("2.Remove Tests");
            System.out.println("3.Modify Tests:");
            System.out.println("4.Display Tests");
            System.out.println("5.Add Patient Tests:");
            System.out.println("6.Remove Patient Tests:");
            System.out.println("7.Display Patient Tests");
            System.out.println("8.Exit");
            System.out.println("Enter Option");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    addTests();
                    break;
                case 2:
                    removeTests();
                    break;
                case 3:
                    ModifyTests();
                    break;
                case 4:
                    displaytests();
                    break;
                case 5:
                    addPatientTests();
                    break;
                case 6:
                    removePatientTests();
                    break;
                case 7:
                    displayPatientTests();
                    break;
                case 8:
                    return;
            }
        }
    }
}

public class HospitalSystem{
    public static void main(String args[]) {
        Scanner o = new Scanner(System.in);
        Patient p = new Patient();
        Doctor d = new Doctor(0);
        d.DoctorMenu(0);
        Facility fac = new Facility();
        Lab l = new Lab();
        Medical m = new Medical();
        while (true) {
            System.out.println();
            System.out.println("Welcome To The Hospital");
            System.out.println("1.Doctor");
            System.out.println("2.Patient");
            System.out.println("3.Facilities");
            System.out.println("4.Lab");
            System.out.println("5.Medicines");
            System.out.println("6.Exit");
            System.out.println("Enter choice");

            int op = o.nextInt();
            switch (op) {
                case 1:
                    d.DoctorMenu(1);
                    break;
                case 2:
                    l.PatientMenu(1);
                    break;
                case 3:
                    fac.fmenu();
                    break;
                case 4:
                    l.LabMenu();
                    break;
                case 5:
                    m.medicalmenu();
                    break;
                case 6:
                    System.exit(0);
                default:
            }
        }
    }
}