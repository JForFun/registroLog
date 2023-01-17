/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivonildo Da Silva
 */
public class logger 
{
    private final String fileName;
    public logger(String fileName){this.fileName=fileName;}
    public  logger(String fileName,String message,String coment)
    {
        this.fileName=fileName;
        this.log(message, coment);
    }
    
    public void log(String message,String coment)
    {
        //carrega todos os logs já existentes!
        ArrayList<log> logs= this.getLogs();
        try 
        {
            log newLog = new log(message,coment);
            FileOutputStream file = new FileOutputStream(this.fileName);
            ObjectOutputStream outPutStream = new ObjectOutputStream(file);
            //Antes de registrar um novo  log, carrega todos os Logs já existentes
            for(int i=0;i<logs.size();i++)
            {
                outPutStream.writeObject(logs.get(i));
            }
            outPutStream.writeObject(newLog);
            file.close();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Carrega todos os logs existentes!
    public ArrayList<log> getLogs()
    {
        ArrayList<log> logs= new ArrayList<>();
        try
        {
            FileInputStream file = new  FileInputStream(this.fileName);
            ObjectInputStream fileInput= new ObjectInputStream(file);
            boolean endOfFile=false;
            while(!endOfFile)
            {
                try
                {
                    logs.add((log) fileInput.readObject());
                }
                catch(EOFException e)
                {
                    endOfFile=true;
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        catch (IOException ex) {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return logs;
    }
}
