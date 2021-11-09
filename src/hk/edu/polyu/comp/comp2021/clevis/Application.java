package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.Scanner;

public class Application {

    public static void main(String[] args){
        //Clevis clevis = Clevis.retrieveClevisFromFile(null);
        Clevis clevis = new Clevis();
        Scanner scanner = new Scanner(System.in);
        while(clevis.isRunning()) {
            clevis.request(scanner.nextLine());
            clevis.saveClevisToFile(null);
        }
    }

}


