/////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Project Name: Study Tracker App
//  Description: The  Study Tracker App is a console-based Java application designed to help 
//               students systematically log, track, summarize, and export their study activities.
//
/////////////////////////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Class Name:   StudyLog
//  Description:  It represents a single study session
//
/////////////////////////////////////////////////////////////////////////////////////////////////

class StudyLog
{
    private LocalDate Date;
    private String Subject;
    private double Duration;
    private String Description;
    

    public StudyLog(LocalDate a, String b, double c, String d)
    {
       this.Date = a;
       this.Subject = b;
       this.Duration = c;
       this.Description = d;
    }

    @Override
    public String toString()
    {
        return Date + " | "+Subject+" "+Duration+" | "+Description;
    }
    public LocalDate getDate()
    {
        return this.Date;
    }
    public String getSubject()
    {
        return this.Subject;
    }
    public double getDuration()
    {
        return this.Duration;
    }
    public String getDescription()
    {
        return this.Description;
    }

}

/////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Class Name:   StudyTracker
//  Description:  It Manages all logs in memory.
//
/////////////////////////////////////////////////////////////////////////////////////////////////
class StudyTracker
{
    public ArrayList <StudyLog> Database;

    public StudyTracker()
    {
        Database = new ArrayList<StudyLog>();

    }
    public void InsertLog()
    {
        Scanner sobj = new Scanner(System.in);
        
        System.out.println("--------------------------------------");
        System.out.println("--Enter the details of your study-----");
        System.out.println("--------------------------------------");

        LocalDate lobj = LocalDate.now();

        System.out.println("We are entering the Date as : "+lobj);

        System.out.println("Enter the name of subject like c/c++/java etc");

        String subj = sobj.nextLine();

        System.out.println("Enter the time period of your study: ");
        double dur = sobj.nextDouble();

        sobj.nextLine();//Issue resolved

        System.out.println("Please provide the description of your study: ");
        String desc = sobj.nextLine();

        StudyLog studyobj = new StudyLog(lobj,subj,dur,desc);

        Database.add(studyobj);

        System.out.println("--Study Log gets Inserted succssfully----");
        System.out.println("-----------------------------------------");

    } 
    public void DisplayLog()
    {
        System.out.println("-----------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("-----------------------------------------");
            return;
        }

        System.out.println("--Log Report of Marvellous Study tracker---");
        System.out.println("-------------------------------------------");

        for(StudyLog s : Database)
        { 
            System.out.println(s);

        }
        System.out.println("-------------------------------------------");
        
    }

    public void ExportToCSV()
    {
        Scanner sobj = new Scanner(System.in);

        System.out.println("Enter the name that you want to create for csv file");

        String FileName = sobj.nextLine();

        System.out.println("-----------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to export -  as database is empty");
            System.out.println("-----------------------------------------");
            return;
        }

        try(FileWriter fwobj = new FileWriter(FileName))
        {
            fwobj.write("Date,Subject,Duration of Study,Descrption of Study\n");

            for(StudyLog s : Database)
            {
                fwobj.write(s.getDate()+","+
                            s.getSubject()+","+
                            s.getDuration()+","+
                            s.getDescription()+"\n");

            }
            System.out.println("Data gets exported to csv successfully.");
            System.out.println("-----------------------------------------");
        }
        catch(IOException iobj)
        {
            System.out.println(iobj);
        }
        catch(Exception eobj)
        {
            System.out.println(eobj);
        }
    }
    public void SummaryByDate()
    {
        System.out.println("-----------------------------------------");
        System.out.println("---Summary by Date from study tracker----");
        System.out.println("-----------------------------------------");

        TreeMap <LocalDate,Double> tobj = new TreeMap<LocalDate, Double>();

        LocalDate lobj = null;

        double d = 0.0;

        double old = 0.0;

        for(StudyLog s: Database)
        {
            lobj = s.getDate();
            d = s.getDuration();

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj, d+old);

            }
            else
            {
                tobj.put(lobj,d);
            }

        } 
        //Display the details as per date
        for(LocalDate l: tobj.keySet())
        {
            System.out.println("Date : "+l+" Total study duration: "+tobj.get(l));
        }


        System.out.println("-----------------------------------------");

    }
    public void SummaryBySubject()
    {
        System.out.println("-----------------------------------------");
        System.out.println("---Summary by Subject from study tracker----");
        System.out.println("-----------------------------------------");

        TreeMap <String, Double> tobj = new TreeMap<String, Double>();

        String sobj = null;

        double d = 0.0;

        double old = 0.0;

        for(StudyLog s: Database)
        {
            sobj = s.getSubject();
            d = s.getDuration();

            if(tobj.containsKey(sobj))
            {
                old = tobj.get(sobj);
                tobj.put(sobj, d+old);

            }
            else
            {
                tobj.put(sobj,d);
            }

        } 
        //Display the details as per subject
        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" Total study duration: "+tobj.get(str));
        }


        System.out.println("-----------------------------------------");
        
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Class Name:   StudyTrackerApp
//  Description: - It Contains main() method.
//               - Handles menu-driven interface and user input.
//               - Calls appropriate methods from StudyTracker.
//
/////////////////////////////////////////////////////////////////////////////////////////////////

class StudyTrackerApp
{
    public static void main(String A[]) 
    {
      int iChoice = 0;

      StudyTracker stobj = new StudyTracker();
      Scanner sobj = new Scanner(System.in);

      System.out.println("--------------------------------------");
      System.out.println("--Welcome to Marvellous Study Tracker--");
      System.out.println("--------------------------------------");


     //Shell to interact with end user
      do
      {
            System.out.println("--------------------------------------");
            System.out.println("Please select appropriate option: ");
            System.out.println("--------------------------------------");

            System.out.println("1: Insert new study log");
            System.out.println("2: Display all study log");
            System.out.println("3: Exports study log to csv");
            System.out.println("4: Summary of study log by date");
            System.out.println("5: Summary of study log by subject");
            System.out.println("6: Exit the application");
            System.out.println("--------------------------------------");
            
      
              iChoice = sobj.nextInt();

              switch(iChoice)
              {     
                    //Insert new log
                    case 1: 
                        stobj.InsertLog();
                        break;

                    //View all study log
                    case 2: 
                    stobj.DisplayLog();
                        break;

                    // Export to csv
                    case 3: 
                        stobj.ExportToCSV();
                        break;

                    //Summary by Date
                    case 4: 
                        stobj.SummaryByDate();
                        break;

                    //Summary by Subject
                    case 5: 
                        stobj.SummaryBySubject();
                        break;

                    //Terminate the project
                    case 6: 
                        break;

                    default:
                        System.out.println("Please enter a valid option");
                        break;
                
              }

      }while(iChoice != 6);

      System.out.println("--------------------------------------------");
      System.out.println("-Thank for using  Marvellous Study Tracker--");
      System.out.println("--------------------------------------------");

    }
}



/*
Modification needed: 

-Ask user about specific subject detail and display details.
-Delete log

*/