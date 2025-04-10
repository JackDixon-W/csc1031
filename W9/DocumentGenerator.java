//package W9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Reader {
    Scanner scanner = new Scanner(System.in);
}

abstract class Document implements Reader{
    protected List<String> content = new ArrayList<>();
    protected String documentType;

    // Template method
    public final void generateDocument() {
        createHeader();
        createBody();
        createFooter();
        printDocument();
    }
    
    private void createHeader() {
        System.out.print("Enter company name: ");
        String companyName = scanner.nextLine();
        if (companyName.isEmpty()) throw new IllegalArgumentException("Error: Company name cannot be empty.");

        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        if (date.isEmpty()) throw new IllegalArgumentException("Date cannot be empty.");

        // Add lines to the document
        content.add("Company: " + companyName);
        content.add("Date: " + date);
    }

    public abstract void createBody();
    public abstract void createFooter();

    public void printDocument() {
        System.out.println("\n=== Printing Document ===");
        System.out.println("=== " + this.documentType + " ===");
        for (String line : this.content) {
            System.out.println(line);
        }
        System.out.println("=========================");
    }
}

class Invoice extends Document {
    public Invoice(){
        this.documentType = "INVOICE";
    }

    @Override
    public void createBody(){
        System.out.print("Enter total amount: ");
        String inputString = scanner.nextLine();

        float totalDue;
        try {
            totalDue = Float.parseFloat(inputString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Total amount must be numeric.");
        }

        if (totalDue <= 0) throw new IllegalArgumentException("Error: Total amount must be positive.");
        
        content.add("Total Due: €" + Float.toString(totalDue));
    }

    @Override
    public void createFooter(){
        content.add("Prepared by: AutoDoc System");
        content.add("Document Type: INVOICE");
    }
}

class Report extends Document {
    public Report() {
        this.documentType = "REPORT";
    }

    @Override
    public void createBody(){
        System.out.print("Enter report summary: ");
        String inputString = scanner.nextLine();
        if (inputString.isEmpty()) System.out.println("Warning: Summary is empty.");
        content.add("Report Summary: " + inputString);
    }

    @Override
    public void createFooter(){
        content.add("Reviewed by: Management Department");
    }
}

class Receipt extends Document {
    public Receipt() {
        this.documentType = "RECEIPT";
    }

    @Override
    public void createBody(){
        System.out.print("Enter amount paid: ");
        String inputString = scanner.nextLine();

        float totalPaid;
        try {
            totalPaid = Float.parseFloat(inputString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount paid must be numeric.");
        }
        if(totalPaid < 0) throw new IllegalArgumentException("Amount paid must be positive.");
        content.add("Total Paid: €" + Float.toString(totalPaid));

        System.out.print("Enter number of items: ");
        inputString = scanner.nextLine();
        int itemsPurchased;
        try {
            itemsPurchased = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Items count must be positive.");
        }
        if(itemsPurchased <= 0) throw new IllegalArgumentException("Error: Items count must be positive.");

        content.add("Items Purchased: " + inputString);
        float pricePerItem;
        try {
            pricePerItem = totalPaid / itemsPurchased;
        } catch (Exception e) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        content.add("Price per Item: €" + Float.toString(pricePerItem));
    }

    @Override
    public void createFooter(){
        content.add("Prepared by: AutoDoc System");
        content.add("Document Type: RECEIPT");
    }
}

public class DocumentGenerator implements Reader{
    public static void main(String[] args) {
        try {
            System.out.println("Choose document type: (INV) Invoice, (REP) Report, (REC) Receipt");
            String choice = scanner.nextLine();
            Document document;
            switch (choice) {
                case "INV":
                    document = new Invoice();
                    break;
                case "REP":
                    document = new Report();
                    break;
                case "REC":
                    document = new Receipt();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice. Exiting.");
            }
            document.generateDocument();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}