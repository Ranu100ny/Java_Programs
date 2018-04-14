package com.bridgelabz.oop.cliniqueUsingOOP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CliniqueManager  {
	private static String doctorFile = "/home/brideit/files/Clinique/doctor.json";
	private static String patientFile = "/home/brideit/files/Clinique/patient.json";
	private static String appointmentFile = "/home/brideit/files/Clinique/appointment.json";
	private List<Doctor> doctorList = new ArrayList<Doctor>();
	private List<Patient> patientList = new ArrayList<Patient>();
	private List<Appointment> appointmentList = new ArrayList<Appointment>();
	ObjectMapper mapper = new ObjectMapper();

	public void add() throws IOException {
		System.out.print("\n\t\t\t\t           A D D-D E T A I L S");
		System.out.println("\n\t\t\t\t     -----------------------------");
		System.out.println("\t\t\t\t          Add Doctor  : Enter 1 ");
		System.out.println("\t\t\t\t          Add Patient : Enter 2 ");
		System.out.print("\n\t\t\t       Enter the Choice Which You Want to Add : ");
		int choice = SingletonOfUtility.getInstance().inputInteger();

		switch (choice) {

		case 1:
			addDoctor();
			break;
		case 2:
			addPatient();
			break;
		default:
			System.out.println("Enter correct choice please...");
		}

	}

	public void addPatient() throws IOException {
		patientList = SingletonOfUtility.getInstance().readFile(patientFile, Patient[].class);

		System.out.println("\n\t\t\t\t    P A T I E N T-D E T A I L S");
		System.out.println("\t\t\t\t---------------------------------");

		Patient patient = new Patient();
		patient.setPatientId(patientList.size() + 1);

		System.out.print("\t\t\t\tEnter Patient Name          : ");
		patient.setPatientName(SingletonOfUtility.getInstance().inputString2());

		System.out.print("\t\t\t\tEnter Patient Mobile Number : ");
		patient.setPatientMobileNo(SingletonOfUtility.getInstance().inputLong());

		System.out.print("\t\t\t\tEnter Patient Age           : ");
		patient.setPatientAge(SingletonOfUtility.getInstance().inputInteger());

		boolean flag = false;
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getPatientName().equals(patient.getPatientName())
					&& patientList.get(i).getPatientMobileNo() == patient.getPatientMobileNo()) {
				flag = true;
			}
		}

		if (!flag) {
			patientList.add(patient);
			System.out.println();
			System.out.println("Patient List : " + patientList);
		} else {
			System.out.println("\n\t\t\t\tPatient is Already exists in the Clinique...!");
		}

		mapper.writeValue(new FileOutputStream(patientFile), patientList);
	}

	public void addDoctor() throws JsonParseException, JsonMappingException, IOException {
		doctorList = SingletonOfUtility.getInstance().readFile(doctorFile, Doctor[].class);

		System.out.println("\n\t\t\t\t    D O C T O R-D E T A I L S");
		System.out.println("\t\t\t\t--------------------------------");

		Doctor doctor = new Doctor();
		doctor.setDoctorId(doctorList.size() + 1);

		System.out.print("\t\t\t\tEnter Doctor Name              : ");
		doctor.setDoctorName(SingletonOfUtility.getInstance().inputString2());

		System.out.print("\t\t\t\tEnter Doctor Specialization    : ");
		doctor.setDoctorSpecialization(SingletonOfUtility.getInstance().inputString2());

		System.out.print("\t\t\t\tEnter Doctor Availability Time : ");
		doctor.setDoctorAvailability(SingletonOfUtility.getInstance().inputString2());
		doctor.setCount(0);
		doctor.setpatientCount(0);
		boolean flag = false;
		for (int i = 0; i < doctorList.size(); i++) {
			if (doctorList.get(i).getDoctorName().equals(doctor.getDoctorName())
					&& doctorList.get(i).getDoctorSpecialization().equals(doctor.getDoctorSpecialization())) {
				flag = true;
			}
		}

		if (!flag) {
			doctorList.add(doctor);
			System.out.println();
			System.out.println("DoctorList : " + doctorList);
		} else {
			System.out.println("\n\t\t\t\tDoctor is Already exists in the clinique...Please Check Doctor List then After Add Doctor!");
			displayDoctor();
		}

		mapper.writeValue(new FileOutputStream(doctorFile), doctorList);

	}

	public void search() throws IOException {
		System.out.print("\n\t\t\t\t       S E A R C H-D E T A I L S");
		System.out.println("\n\t\t\t\t     -----------------------------");
		System.out.println("\t\t\t\t          To Doctor  : Enter 1 ");
		System.out.println("\t\t\t\t          To Patient : Enter 2 ");
		System.out.print("\n\t\t\t       Enter the Choice Which You Want to Search : ");
		int choice = SingletonOfUtility.getInstance().inputInteger();

		switch (choice) {

		case 1:
			doctorDetails();
			break;
		case 2:
			patientDetails();
			break;
		default:
			System.out.println("Enter correct choice please...");
		}

	}

	public List<Patient> patientDetails() throws IOException {
		System.out.println("\n\t\t\t\t        P A T I E N T-D E T A I L S");
		System.out.println("\t\t\t\t      -------------------------------");
		System.out.println("\t\t\t\t        By Name           : Enter 1");
		System.out.println("\t\t\t\t        By Id             : Enter 2");
		System.out.println("\t\t\t\t        By Mobile Number  : Enter 3");
		System.out.print("\n\t\t\t\tEnter the Choice Which You want to Serach : ");
		int choice = SingletonOfUtility.getInstance().inputInteger();
		String name = "", id = "", mobile = "", result = "";

		switch (choice) {

		case 1:
			System.out.print("\n\t\t\t\tEnter Name of the Patient     : ");
			name = SingletonOfUtility.getInstance().inputString2();
			result = name;
			break;
		case 2:
			System.out.print("\n\t\t\t\tEnter Id of the Patient       : ");
			id = SingletonOfUtility.getInstance().inputString2();
			result = id;
			break;
		case 3:
			System.out.print("\n\t\t\t\tEnter MobileNo of the Patient : ");
			mobile = SingletonOfUtility.getInstance().inputString2();
			result = mobile;
			break;
		default:
			System.out.println("\n\t\t\t\tInvalid Choice...Please Try Again!");
		}

		patientList = SingletonOfUtility.getInstance().readFile(patientFile, Patient[].class);

		boolean flag = SingletonOfUtility.getInstance().toCheckPatientDetails(patientList, result);

		patientList = printPatientList(patientList, result, flag, choice);

		return patientList;
	}

	private List<Patient> printPatientList(List<Patient> patientList2, String result, boolean flag,
			int choice) {
		if (flag) {
			System.out.println();

			System.out.println("\t\t\t\t                 P A T I E N T-L I S T");
			System.out.println("\t\t\t\t----------------------------------------------------");
			System.out.println("\t\t\t\tPatient_Name | Patient_Id  | Patient_Mobile_Number");

			for (int i = 0; i < patientList2.size(); i++) {
				switch (choice) {
				case 1:
					if (result.equals(patientList2.get(i).getPatientName())) {
						System.out.printf("%40s %10s %20s ", patientList2.get(i).getPatientName(),
								patientList2.get(i).getPatientId(), patientList2.get(i).getPatientMobileNo());
						System.out.println();
					}
					break;

				case 2:
					if (result.equals(String.valueOf(patientList2.get(i).getPatientId()))) {
						System.out.printf("%40s %10s %20s ", patientList2.get(i).getPatientName(),
								patientList2.get(i).getPatientId(), patientList2.get(i).getPatientMobileNo());
						System.out.println();
					}

					break;

				case 3:
					if (result.equals(String.valueOf(patientList2.get(i).getPatientMobileNo()))) {

						System.out.printf("%40s %10s %20s ", patientList2.get(i).getPatientName(),
								patientList2.get(i).getPatientId(), patientList2.get(i).getPatientMobileNo());
						System.out.println();
					}

					break;
				default:
					System.out.println("Invalid Choice...!");

				}

			}
		} else {
			switch (choice) {
			case 1:
				System.out.print("\n\t\t\t\t   The Patient Name is not Valid...!");

				break;

			case 2:
				System.out.print("\n\t\t\t\t  The Patient Id is not Valid...! ");
				break;

			case 3:
				System.out.print("\n\t\t\t\t   The Patient Mobile Number is not Valid...! ");
				break;

			default:
				System.out.println("Invalid Choice...Please Try Again!");
			}

		}
		return patientList2;

	}

	public List<Doctor> doctorDetails() throws IOException {
		System.out.println("\n\t\t\t\t      D O C T O R-D E T A I L S");
		System.out.println("\t\t\t\t    -----------------------------");
		System.out.println("\t\t\t\t     By Name           : Enter 1");
		System.out.println("\t\t\t\t     By Id             : Enter 2");
		System.out.println("\t\t\t\t     By Specialization : Enter 3");
		System.out.println("\t\t\t\t     By Availability   : Enter 4");
		System.out.print("\n\t\t\t\tEnter the Choice Which You want to Serach : ");
		int choice = SingletonOfUtility.getInstance().inputInteger();
		String name = "", specialization = "", availability = "";
		int id = 0;
		String result = "";
		switch (choice) {
		case 1:
			System.out.print("\n\t\t\t\tEnter Name of the Doctor           : ");
			name = SingletonOfUtility.getInstance().inputString2();
			result = name;
			break;
		case 2:
			System.out.print("\n\t\t\t\tEnter Id of the Doctor             : ");
			id = SingletonOfUtility.getInstance().inputInteger();
			result = id + "";
			break;
		case 3:
			System.out.print("\n\t\t\t\tEnter Specialization of the Doctor : ");
			specialization = SingletonOfUtility.getInstance().inputString2();
			result = specialization;
			break;
		case 4:
			System.out.print("\n\t\t\t\tEnter Availability of the Doctor   : ");
			availability = SingletonOfUtility.getInstance().inputString2();
			result = availability;
			break;

		default:
			System.out.print("\n\t\t\t\tInvalid Choice...!");
		}

		doctorList = SingletonOfUtility.getInstance().readFile(doctorFile, Doctor[].class);

		boolean flag = SingletonOfUtility.getInstance().toCheckDoctorDetails(doctorList, result);

		doctorList = printDoctorList(doctorList, result, choice, flag);

		return doctorList;
	}

	private List<Doctor> printDoctorList(List<Doctor> doctorList2, String result, int choice, boolean flag) {
		if (flag) {
			System.out.println();
			System.out.println("\t\t\t\t                         D O C T O R -L I S T");
			System.out.println("\t\t\t\t----------------------------------------------------------------------");
			System.out.println("\t\t\t\tDoctor_Name    | Doctor_Id  | Doctor_Specialization | Doctor_Availability");

			for (int i = 0; i < doctorList2.size(); i++) {
				switch (choice) {
				case 1:
					if (result.equals(doctorList2.get(i).getDoctorName())) {
						System.out.printf("%46s %8s %20s %20s ", doctorList2.get(i).getDoctorName(),
								doctorList2.get(i).getDoctorId(), doctorList2.get(i).getDoctorSpecialization(),
								doctorList2.get(i).getDoctorAvailability());
						System.out.println();
					}
					break;

				case 2:
					if (result.equals(String.valueOf(doctorList2.get(i).getDoctorId()))) {
						System.out.printf("%46s %8s %20s %20s ", doctorList2.get(i).getDoctorName(),
								doctorList2.get(i).getDoctorId(), doctorList2.get(i).getDoctorSpecialization(),
								doctorList2.get(i).getDoctorAvailability());
						System.out.println();
					}

					break;

				case 3:
					if (result.equals(doctorList2.get(i).getDoctorSpecialization())) {

						System.out.printf("%46s %8s %20s %20s ", doctorList2.get(i).getDoctorName(),
								doctorList2.get(i).getDoctorId(), doctorList2.get(i).getDoctorSpecialization(),
								doctorList2.get(i).getDoctorAvailability());
						System.out.println();
					}

					break;

				case 4:
					if (result.equals(doctorList2.get(i).getDoctorAvailability())) {
						System.out.printf("%46s %8s %20s %20s ", doctorList2.get(i).getDoctorName(),
								doctorList2.get(i).getDoctorId(), doctorList2.get(i).getDoctorSpecialization(),
								doctorList2.get(i).getDoctorAvailability());
						System.out.println();
					}

					break;

				default:
					System.out.println("Invalid Choice...Please Try Again!");

				}

			}
			return doctorList2;
		} else {
			switch (choice) {
			case 1:
				System.out.print("\n\t\t\t\t  The Doctor Name is not Valid...!");
				break;

			case 2:
				System.out.print("\n\t\t\t\t  The Doctor Id is not Valid...! ");
				break;

			case 3:
				System.out.print("\n\t\t\t\t  The Doctor Specialization is not Valid...! ");
				break;

			case 4:
				System.out.print("\n\t\t\t\t  The Doctor Availability is not Valid...! ");
				break;

			default:
				System.out.println("Invalid Choice Please Enter correct Choice...!");
			}

		}
		return doctorList2;

	}

	public void display() throws IOException {
		System.out.print("\n\t\t\t\t       D I S P L A Y-D E T A I L S");
		System.out.println("\n\t\t\t\t      -----------------------------");
		System.out.println("\t\t\t\t        Dispaly Doctor  : Enter 1 ");
		System.out.println("\t\t\t\t        Display Patient : Enter 2 ");
		System.out.print("\n\t\t\t       Enter the Choice Which You Want to Display : ");
		int choice = SingletonOfUtility.getInstance().inputInteger();

		switch (choice) {

		case 1:
			displayDoctor();
			break;
		case 2:
			displayPatient();
			break;
		default:
			System.out.println("Enter correct choice please...");
		}

	}

	public void displayPatient() throws IOException {
		System.out.println();
		System.out.println("\t\t\t\t               P A T I E N T -L I S T");
		System.out.println("\t\t\t\t--------------------------------------------------");
		System.out.println("\t\t\t\tPatient_Name | Patient_Id  | Patient_Mobile_Number");

		patientList = SingletonOfUtility.getInstance().readFile(patientFile, Patient[].class);
		for (int i = 0; i < patientList.size(); i++) {
			System.out.printf("%40s %10s %20s ", patientList.get(i).getPatientName(), patientList.get(i).getPatientId(),
					patientList.get(i).getPatientMobileNo());
			System.out.println();

		}
	}

	public void displayDoctor() throws IOException {
		System.out.println();
		System.out.println("\t\t\t\t                          D O C T O R -L I S T ");
		System.out.println("\t\t\t\t-----------------------------------------------------------------------------");
		System.out.println("\t\t\t\tDisplay_Doctor_Name | Doctor_Id  | Doctor_Specialization | Doctor_Availability");
		doctorList = SingletonOfUtility.getInstance().readFile(doctorFile, Doctor[].class);
		for (int i = 0; i < doctorList.size(); i++) {

			System.out.printf("%46s %8s %20s %20s ", doctorList.get(i).getDoctorName(), doctorList.get(i).getDoctorId(),
					doctorList.get(i).getDoctorSpecialization(), doctorList.get(i).getDoctorAvailability());
			System.out.println();
		}

	}

	public void popularDoctor() throws JsonParseException, JsonMappingException, IOException {
		String doctorName = "", specialization = "";
		int numberOfPatient = 0;
		doctorList = SingletonOfUtility.getInstance().readFile(doctorFile, Doctor[].class);

		int max = doctorList.get(0).getpatientCount();
		for (int i = 1; i < doctorList.size(); i++) {
			if (max < doctorList.get(i).getpatientCount()) {
				max = doctorList.get(i).getpatientCount();
				doctorName = doctorList.get(i).getDoctorName();
				specialization = doctorList.get(i).getDoctorSpecialization();
				numberOfPatient = doctorList.get(i).getpatientCount();
			}
		}
		System.out.println();
		System.out.println("\t\t\t\t                    F A M O U S-D O C T O R");
		System.out.println("\t\t\t\t---------------------------------------------------------------");
		System.out.println("\t\t\t\tDisplay_Doctor_Name | Doctor_Specialization | Number_oF_Patient");
		System.out.printf("%46s %20s %18d ", doctorName, specialization, numberOfPatient);
		System.out.println();

	}

	public void takeAppointment() throws IOException {
		Appointment appointment = new Appointment();
		doctorList = doctorDetails();

		System.out.println();
		System.out.print("\n\t\t\t\tEnter the Patient Name : ");
		appointment.setPatientName(SingletonOfUtility.getInstance().inputString2());
		patientList = SingletonOfUtility.getInstance().readFile(patientFile, Patient[].class);
		for (int i = 0; i < patientList.size(); i++) {
			if (appointment.getPatientName().equals(patientList.get(i).getPatientName())) {
				appointment.setPatientId(patientList.get(i).getPatientId());
			}
		}

		boolean flag = SingletonOfUtility.getInstance().toCheckPatientDetails(patientList,
				appointment.getPatientName());

		if (flag) {
			System.out.println();
			System.out.println("Doctor : " + doctorList);
			System.out.print("\n\t\t\t\tEnter the id Which Doctor you want to Appointment : ");
			int doctorId = SingletonOfUtility.getInstance().inputInteger();
			appointment.setDoctorId(doctorId);
			for (int i = 0; i < doctorList.size(); i++) {
				if (doctorId == doctorList.get(i).getDoctorId()) {
					appointment.setDoctorName(doctorList.get(i).getDoctorName());
					appointment.setDoctorAvailability(doctorList.get(i).getDoctorAvailability());
				}
			}

			appointmentList = SingletonOfUtility.getInstance().readFile(appointmentFile, Appointment[].class);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			
			appointment.setDate(simpleDateFormat.format(date));

			int count = 0;
			for (int i = 0; i < doctorList.size(); i++) {
				if (appointment.getDoctorId() == doctorList.get(i).getDoctorId()
						&& appointment.getDoctorName().equals(doctorList.get(i).getDoctorName())) {
					count = doctorList.get(i).getCount();

					if (count < 5) {
						count++;
						doctorList.get(i).setpatientCount(doctorList.get(i).getpatientCount() + 1);
						doctorList.get(i).setCount(count);
						mapper.writeValue(new FileOutputStream(doctorFile), doctorList);
						appointmentList.add(appointment);
						System.out.println("\n\t\t\t\tAppointment is successfully Done...!");
						mapper.writeValue(new FileOutputStream(appointmentFile), appointmentList);

					} else {

							System.out.println("id    : "+appointment.getDoctorId());
							System.out.println("date1 : "+appointment.getDate());
							System.out.println("date2 : "+simpleDateFormat.format(date));
							if (doctorId == appointment.getDoctorId()
									&& simpleDateFormat.format(date).equals(appointment.getDate())) {
								count = 0;
								doctorList.get(i).setCount(count);
								System.out.println("r1");
								mapper.writeValue(new FileOutputStream(doctorFile), doctorList);
							
						}
						 boolean status=false;	
						 for(int p=0;p<doctorList.size();p++)
						 {
						  if(doctorId==doctorList.get(p).getDoctorId() && doctorList.get(p).getCount()==0)
						  {
						   status=true;	
						   doctorList.get(i).setpatientCount(doctorList.get(i).getpatientCount() + 1);
						doctorList.get(i).setCount(count);
							
						  }
						 }
						 
						 if(status)
						 {
							 
						 }
						 else
						 {
						System.out.print(
								"\n\t\t\t\tDoctor is Already has 5 Appointment....To Check Another Doctor(Y/N) ");
						System.out.println();
						displayDoctor();
						System.out.println();
						doctorDetails();
						 }
					}
				}

			}

		} else {
			displayPatient();
			System.out.print("\n\t\t\t\tPatient is not Available...To Add Patient First then Take Appointment(Y/N) ");
			System.out.println();
			addPatient();
		}
	}

}
