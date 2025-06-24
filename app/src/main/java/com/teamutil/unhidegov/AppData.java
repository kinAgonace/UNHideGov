package com.teamutil.unhidegov;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.HashMap;

public class AppData {
    static ArrayList<Project> projects = new ArrayList<>();
    static ArrayList<Project> dynamic = new ArrayList<>();
    public AppData (Context context){
        {
            Project project = new Project(context);
            project.setName("Malasakit BRIDGE");
            project.setDescription("This bridge was built to commemorate the countless children that Kibuloy’s touched in their hearts. ");
            project.setImage(R.drawable.kibuloy);
            project.setLocation(Project.PROVINCIAL);
            project.setDate(20222);

            project.setTitle("Project Proposer");
            project.setDetails("Belle");
            project.setTime("7 Months");
            project.setProgress(70);
            project.setBudget(90000000,"labor - 28\nmaterials - 32\nEquipment (Rental or purchase) - 20\nOverhead Costs - 8\nRegulations, permits and associated costs - 3\nAdditional vendor costs - 5\nContingency and risk management budget - 7");


            projects.add(project);
        } {
            Project project = new Project(context);
            project.setName("Rizal Gen. HOSPITAL");
            project.setDescription("A General Hospital made to be accessible to even the poorest of citizens.");
            project.setImage(R.drawable.hospital);
            project.setLocation(Project.PROVINCIAL);
            project.setDate(20156);



            project.setTitle("Chief Engineers");
            project.setDetails("Larcen Belangel");
            project.setTime("2 Years");
            project.setProgress(90);
            project.setBudget(100000000,"labor - 28\nmaterials - 22\nEquipment (Rental or purchase) - 30\nOverhead Costs - 3\nRegulations, permits and associated costs - 8\nAdditional vendor costs - 5\nContingency and risk management budget - 7");


            projects.add(project);
        } {
            Project project = new Project(context);
            project.setName("Luna Memorial");
            project.setDescription("A tall and upstanding memorial dedicated to our astounding war hero");
            project.setImage(R.drawable.luna);
            project.setLocation(Project.LOCAL);
            project.setDate(20134);

            project.setTitle("Chief Engineers");
            project.setDetails("Ludy Yales and Lee Andrew Garcia");
            project.setTime("1 Year 2 Months");
            project.setProgress(60);
            project.setBudget(5000000,"labor - 38\nmaterials - 22\nEquipment (Rental or purchase) - 10\nOverhead Costs - 8\nRegulations, permits and associated costs - 3\nAdditional vendor costs - 15\nContingency and risk management budget - 7");


            projects.add(project);
        }{
            Project project = new Project(context);
            project.setName("New Metro Manila Re-education Center");
            project.setDescription("A large campus made solely to support the common citizen with their educational needs");
            project.setImage(R.drawable.school);
            project.setLocation(Project.PROVINCIAL);
            project.setDate(20108);

            project.setTitle("Project Proposers");
            project.setDetails("Antonio Calopez");
            project.setTime("1 Year");
            project.setProgress(50);
            project.setBudget(8000000,"labor - 28\nmaterials - 32\nEquipment (Rental or purchase) - 20\nOverhead Costs - 8\nRegulations, permits and associated costs - 3\nAdditional vendor costs - 5\nContingency and risk management budget - 7");



            projects.add(project);
        }{
            Project project = new Project(context);
            project.setName("Taguig-Pasay Career Manhunt");
            project.setDescription("To support the lack of manpower in the energy sector, the LGU's of Taguig and Pasay joined together to commence a widespread jobfair");
            project.setImage(R.drawable.jobfair);
            project.setLocation(Project.LOCAL);
            project.setDate(20179);

            project.setTitle("General Employers");
            project.setDetails("Ariane Patindol and Rax Andales");
            project.setTime("1 Week");
            project.setProgress(30);


            projects.add(project);
        }{
            Project project = new Project(context);
            project.setName("Scholar ng Bayan");
            project.setDescription("This Scholarship program commamorates to Nico's son who got into a car accident.");
            project.setImage(R.drawable.educ);
            project.setLocation(Project.NATIONAL);
            project.setDate(20095);

            project.setTitle("Project Proposers");
            project.setDetails("Nico Ayupan and Medrick Torre");
            project.setTime("4 Years");
            project.setProgress(80);


            projects.add(project);
        }{
            Project project = new Project(context);
            project.setName("Las Pinas Bazar and Market");
            project.setDescription("The new central market offering a wide variety of goods from agricultural products to dry goods.");
            project.setImage(R.drawable.money);
            project.setLocation(Project.LOCAL);
            project.setDate(20460);

            project.setTitle("Chief Engineer and Architect");
            project.setDetails("Abrielle Tan and James Batadlan");
            project.setTime("1 Year and 3 Months");
            project.setProgress(40);


            projects.add(project);
        }
    }
}
