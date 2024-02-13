import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter the name of the department: ");
        String department = sc.nextLine();

        System.out.println("Enter worker data ");
        System.out.print("Enter the name: ");
        String name = sc.nextLine();
        System.out.print("Enter the level: ");
        String level = sc.nextLine();
        System.out.print("Enter the base salary: ");
        Double baseSalary = sc.nextDouble();
        sc.nextLine();
        Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Department(department));

        System.out.print("How many contracts to this worker? ");
        int n = sc.nextInt();

        for (int i=1; i <= n; i++){
            sc.nextLine();
            System.out.println("Enter contract #"+i+" data:");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());
            sc.nextLine();
            System.out.print("Value per hour: ");
            Double valuePerHour = sc.nextDouble();
            sc.nextLine();
            System.out.print("Duration (hours):");
            int hours = sc.nextInt();

            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.println();
        System.out.print("Enter the month and the year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3, 7));

        System.out.println();
        System.out.println("Name: "+worker.getName());
        System.out.println("Department: " +worker.getDepartment().getName());
        System.out.println("Income for "+monthAndYear+":" +String.format("%.2f",worker.income(year, month)));

    }
}