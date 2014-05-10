package com.example.student;

import java.io.IOException;

import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.HttpServer;
import org.apache.avro.ipc.specific.SpecificResponder;

import com.avro.protocol.student.Major;
import com.avro.protocol.student.Student;
import com.avro.protocol.student.StudentService;

/**
 * This is the implementation of Student Service.
 * @author hardikarora
 */
public class StudentServiceImpl implements StudentService{
	
	private static final int SERVER_PORT = 9090;

	public static void main(String[] args)
	{
		StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
		try {
			//starting the server
			studentServiceImpl.startServer();
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * helper method to start the server
	 * @throws IOException
	 */
	public void startServer() throws IOException{

		HttpServer server = new HttpServer(new SpecificResponder(StudentService.class,
				this),SERVER_PORT);
		server.start();
		System.out.println("The server has been started");
	}
	
	/**
	 * Helper method to send the student to college.
	 */
	public boolean sendToCollege(Student student) throws AvroRemoteException {
		// Here you can do whatever you need to send the student to college.
		if(student.getMajor().equals(Major.COMPUTER_SCIENCE))
			System.out.println(" the student is going to computer science college");
		
		else if(student.getMajor().equals(Major.BUISSNESS_STUDIES))
			System.out.println(" the student is going to buissness studies college");
		
		else if(student.getMajor().equals(Major.INDUSTRIAL_ENGINEERING))
			System.out.println(" the student is going to industrial engineering college");
		
		else if(student.getMajor().equals(Major.INFORMATION_SYSTEMS))
			System.out.println(" the student is going to information systems college");
		
		else
			return false;
		return true;
	}

	/**
	 * Helper method to get the student information.
	 */
	public CharSequence getStudentInformation(Student student)
			throws AvroRemoteException {
		// you can process the student information here.
		return "INFO : " +  student.getFirstName() + " " + student.getLastName() ;
	}
	
}
