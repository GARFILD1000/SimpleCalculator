package com.example;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;
//import PhysicalPerson;
//import LegalPerson;

class PhoneBook{
    static Scanner input;
    
    static class LegalPersonComparator implements Comparator<LegalPerson>{
        public int compare(LegalPerson paramT1, LegalPerson paramT2) {
            if (paramT1.getID() > paramT2.getID()){
                return 1;
            }
            else if (paramT1.getID() < paramT2.getID())
                return -1;
            else
                return 0;
        }
    }
    
    static class PhysicalPersonComparator implements Comparator<PhysicalPerson> {
        public int compare(PhysicalPerson paramT1, PhysicalPerson paramT2) {
            if (paramT1.getID() > paramT2.getID()){
                return 1;
            }
            else if (paramT1.getID() < paramT2.getID())
                return -1;
            else
                return 0;
        }
    }
    
    public static void main(String[] args){
        input = new Scanner(System.in);
        TreeSet<LegalPerson> legalPersonTree = new TreeSet<LegalPerson>(new LegalPersonComparator()); 
        TreeSet<PhysicalPerson> physicalPersonTree = new TreeSet<PhysicalPerson>(new PhysicalPersonComparator());
        legalPersonTree.add(new LegalPerson("Bulgakov Dmitriy Olegovich","3506800","Pushkina-Kolotushkina","1435236257"));
        physicalPersonTree.add(new PhysicalPerson("Teslenok Roman Konstantinovich","3502100","Petrovskaya-Rasumovskaya","88005553535"));
        while(true){
            if (mainCycle(legalPersonTree, physicalPersonTree) <= 0) break;
        }
    }

    
    public static <E> String readDatabase(String fileName){
        clearConsole();
        FileReader fr;
        StringBuffer stringBuffer = new StringBuffer("");
        try{
            fr = new FileReader(fileName);
            char[] buffer = new char[3];
            int result = fr.read(buffer, 0, 3);
            while(result > 0){
                stringBuffer = stringBuffer.append(String.valueOf(buffer));
                result = fr.read(buffer, 0, 3);
            }
            System.out.println("Readed from " + fileName + "\n" + stringBuffer);
            fr.close();
        }
        catch(IOException error){
            String exception = error.getMessage();
            System.out.println("Error reading from" + fileName + "\n" + exception);
        };
        return stringBuffer.toString();
    }

    public static void printAllPersons(TreeSet<LegalPerson> legalPersonTree, TreeSet<PhysicalPerson> physicalPersonTree){
        clearConsole();
        System.out.println("<><><> Legal Persons: <><><>");
        if (legalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(LegalPerson x: legalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        System.out.println("");
        
        System.out.println("<><><> Physical Persons: <><><>");
        if (physicalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(PhysicalPerson x: physicalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        
        input.nextLine();
    }
    
    public static int deleteAnyPerson(TreeSet<LegalPerson> legalPersonTree, TreeSet<PhysicalPerson> physicalPersonTree){
        clearConsole();
        System.out.println("Is person to delete Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(legalPersonTree.remove(new LegalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                LegalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(physicalPersonTree.remove(new PhysicalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                PhysicalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    };
    

    public static int addNewPerson(TreeSet<LegalPerson> legalPersonTree, TreeSet<PhysicalPerson> physicalPersonTree){
        clearConsole();
        System.out.println("Is new person Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter INN: ");
            String INN = input.nextLine();
            while(INN.isEmpty()){
                INN = input.nextLine();
            }
            LegalPerson newPerson = new LegalPerson(FIO, Phone, Address, INN);
            if (legalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter Mobile Phone Number: ");
            String mobilePhone = input.nextLine();
            while(mobilePhone.isEmpty()){
                mobilePhone = input.nextLine();
            }
            PhysicalPerson newPerson = new PhysicalPerson(FIO, Phone, Address, mobilePhone);
            if (physicalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    }

    public static void readAllDatabases(TreeSet<LegalPerson> legalPersonTree, TreeSet<PhysicalPerson> physicalPersonTree){
        String stringFromDatabase = readDatabase("LegalPersons.csv");
        String[] lines = stringFromDatabase.split("\n");
        LegalPerson.clear();
        legalPersonTree.clear();
        for(String x: lines){
            LegalPerson newLegalPerson = new LegalPerson("","","","");
            newLegalPerson.fromCSV(x);
            legalPersonTree.add(newLegalPerson);
        }
        stringFromDatabase = readDatabase("PhysicalPersons.csv");
        lines = stringFromDatabase.split("\n");
        PhysicalPerson.clear();
        physicalPersonTree.clear();
        for(String x: lines){
            PhysicalPerson newPhysicalPerson = new PhysicalPerson("","","","");
            newPhysicalPerson.fromCSV(x);
            physicalPersonTree.add(newPhysicalPerson);
        }
        input.nextLine();
        return;
    }

    public static int mainCycle(TreeSet<LegalPerson> legalPersonTree, TreeSet<PhysicalPerson> physicalPersonTree){
        clearConsole();
        System.out.println("<><><> Phone Book Menu <><><>");
        System.out.println("1. Print all persons\n2. Add new person\n3. Delete any person\n4. Read Database\n0. Exit program");
        System.out.print("Enter menu item number: ");
        String buffer = input.nextLine();
        switch(Integer.parseInt(buffer)){
        case 1: 
            printAllPersons(legalPersonTree, physicalPersonTree);
        break; 
        case 2:
            addNewPerson(legalPersonTree, physicalPersonTree);
        break;
        case 3:   
            deleteAnyPerson(legalPersonTree, physicalPersonTree);
        break;
        case 4:
            readAllDatabases(legalPersonTree, physicalPersonTree);
        break;
        case 0:
            return 0;
        default:
            return -1;
        }
        return 5; 
    } 
    
    private static void printPerson(LegalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + "\nINN: " +
                person.getINN());
    }
    
    private static void printPerson(PhysicalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + 
            "\nMobileNumber: " + person.getMobilePhone());
    }
    
    public static void clearConsole(){
        try{
            new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
        }
        catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
    }
}

