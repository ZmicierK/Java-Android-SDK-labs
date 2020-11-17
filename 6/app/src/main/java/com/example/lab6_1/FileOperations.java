package com.example.lab6_1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    public Boolean write(String fileName, String lNameContent, String groupContent, String facultyContent){
        try {

            String fpath = "/sdcard/Lab6/"+fileName+".txt";

            File file = new File(fpath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(lNameContent+", "+groupContent+", "+facultyContent+"/");
            bw.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String read(String fname, String lName, String group, String faculty){

        BufferedReader br = null;
        String response = null;
        int k=0;
        if(!lName.isEmpty()) k++;
        if(!group.isEmpty()) k+=2;
        if(!faculty.isEmpty()) k+=4;

        try {

            StringBuilder output = new StringBuilder();
            String fpath = "/sdcard/Lab6/"+fname+".txt";

            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split("/");
                for(int i = 0; i < split.length; i++) {
                    switch(k){
                        case 0:
                            output.append(split[i]).append("\n");
                            continue;
                        case 1:
                            if(split[i].contains(lName)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                        case 2:
                            if(split[i].contains(group)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                        case 3:
                            if(split[i].contains(lName) & split[i].contains(group)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                        case 4:
                            if(split[i].contains(faculty)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                        case 5:
                            if(split[i].contains(lName) & split[i].contains(faculty)){
                                output.append(split[i]+"\n");
                            }
                            continue;
                        case 6:
                            if(split[i].contains(group) & split[i].contains(faculty)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                        case 7:
                            if(split[i].contains(lName) & split[i].contains(group) & split[i].contains(faculty)){
                                output.append(split[i]).append("\n");
                            }
                            continue;
                    }
                }
            }
            response = output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return response;

    }
}