package ch.creditsuisse.poland.wroclaw.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import ch.creditsuisse.poland.wroclaw.beans.EventLog;
import ch.creditsuisse.poland.wroclaw.beans.LogFile;
import ch.creditsuisse.poland.wroclaw.db.DBConnection;

public class Main {
	//public static final String log_file_path = "src//creditsuisse//poland//wroclaw//main//logfile.txt";
	//public static final String log_file_path = "C:\\Users\\Administrateur\\Desktop\\nezita\\nezita\\nezita\\workspace_e-boutique\\CreditSuisse\\src\\ch\\creditsuisse\\poland\\wroclaw\\main\\logfile.txt";
	public enum state {STARTED,FINISHED};
	public enum type {APPLICATION_LOG};
	public static Connection conn;
	public static void main(String[] args){
		String log_file_path = args[0];
		try {
			 File f = new File(log_file_path);
			 BufferedReader b = new BufferedReader(new FileReader(f));
			 String readLine = "";
			 List<LogFile> listLogFile = new ArrayList<LogFile>();
	            while ((readLine = b.readLine()) != null) {
	            	LogFile logFile = new LogFile();
	            	StringTokenizer token = new StringTokenizer(readLine, ","); 
	            	while(token.hasMoreElements()) {
	            		String property = token.nextToken();
	            		StringTokenizer st1 = new StringTokenizer(property, ":");
	            		while(st1.hasMoreElements()) {
	            			String attribut = st1.nextToken();
	            			if(attribut.equals("{id")) {
	            				String value = st1.nextToken();
	            				logFile.setId(value);
	            			}else if(attribut.equals("state")) {
	            				String value = st1.nextToken();
	            				logFile.setState(value);
	            			}else if(attribut.equals("type")) {
	            				String value = st1.nextToken();
	            				logFile.setType(value);
	            			}else if(attribut.equals("host")) {
	            				String value = st1.nextToken();
	            				logFile.setHost(value);
	            			}else if(attribut.equals("timestamp")) {
	            				String timestamp = st1.nextToken();
	            				BigDecimal value = new BigDecimal(timestamp.substring(0, timestamp.length()-1));
	            				logFile.setTimestamp(value);
	            			}
	            		}
	            	}
	            	listLogFile.add(logFile);
	            }
	            ArrayList<EventLog> listEventLog = new ArrayList<EventLog>();
	            for(int i=0; i< listLogFile.size();i++){
	            	LogFile fileI = (LogFile)listLogFile.get(i);
	            	EventLog eventLog = new EventLog();
	            	for(int j=i+1; j< listLogFile.size();j++) {
	            		LogFile fileJ = (LogFile)listLogFile.get(j);
	            		if(fileI.getId().equals(fileJ.getId())) {
	            			eventLog.setId(fileI.getId());
	            			if(!fileI.getHost().equals("")) {
	            				eventLog.setHost(fileI.getHost());
	            			}else if(!fileJ.getHost().equals("")){
	            				eventLog.setHost(fileJ.getHost());
	            			}
	            			Integer duration=0;
	            			if(fileI.getState().equals("FINISHED")&&fileJ.getState().equals("STARTED")) {
	            				duration = fileI.getTimestamp().subtract(fileJ.getTimestamp()).intValue();
	            				eventLog.setDuration(duration);
	            			}else if(fileI.getState().equals("STARTED")&&fileJ.getState().equals("FINISHED")){
	            				duration = new Integer(fileJ.getTimestamp().subtract(fileI.getTimestamp()).intValue());
	            				eventLog.setDuration(duration);
	            			}
	            			if(fileI.getType()!=null&&!fileI.getType().equals("")) {
            					eventLog.setType(fileI.getType());
	            			}
	            			if(fileJ.getType()!=null&&!fileJ.getType().equals("")) {
	            				eventLog.setType(fileJ.getType());	
	            			}
	            		}
	            	}
	            	listEventLog.add(eventLog);
	            }
	            conn = DBConnection.getConnection();
	            try {
	            	PreparedStatement ps1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `event_log` (`id` varchar(45) unsigned NOT NULL, `duration` int(10),`host` varchar(45) DEFAULT NULL,`type_log` varchar(45) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;");
	            	ResultSet rs1 = ps1.executeQuery();
	            	rs1.next();
	            	Iterator<EventLog> iter = listEventLog.iterator();
	            	while(iter.hasNext()) {
	            		try{
	            			EventLog eventLog = iter.next();
	            			if(eventLog.getDuration()>4) {
	            				eventLog.setFlag(true);
	            			}
	            			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO `event_log` (`id`, `duration`, `host`, `type_log`) VALUES('"+eventLog.getId()+"', "+eventLog.getDuration()+", '"+eventLog.getHost()+"', '"+eventLog.getType()+"'),");
	            			ResultSet rs2 = ps1.executeQuery();
	            			rs2.next();
	            			ps2.close();
	            			rs2.close();
	            		}catch(SQLException el) {
	            			
	            		}
	            	}
	            	ps1.close();
	            	rs1.close();
	            	
	            }catch(SQLException e1) {
	            	
	            }
	            
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
