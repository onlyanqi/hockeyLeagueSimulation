package org.icehockey;

import model.HockeyContext;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {


        String filePath = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide location of JSON file");
        filePath  = scanner.nextLine();

        HockeyContext context = new HockeyContext();
        context.startAction(filePath);


    }
}
