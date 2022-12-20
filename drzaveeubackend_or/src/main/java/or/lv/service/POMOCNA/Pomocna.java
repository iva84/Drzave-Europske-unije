package or.lv.service.POMOCNA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pomocna {

	public static void main(String[] args) {
		Grupa g1 = new Grupa("Prva grupa");
		Grupa g2 = new Grupa("Druga grupa");
		Grupa g3 = new Grupa("Treća grupa");
		List<Grupa> grupe = List.of(g1, g2, g3); 
		
		Student s1 = new Student("Ana", "Jurić");
		Student s2 = new Student("Marija", "Anić");
		Student s3 = new Student("Milica", "Šimić");
		Student s4 = new Student("Ivana", "Ivić");
		Student s5 = new Student("Ivona", "Katić");
		Student s6 = new Student("Anamarija", "Marić");
		Student s7 = new Student("Katarina", "Blažević");
		Student s8 = new Student("Marta", "Šimunović");
		Student s9 = new Student("Kristina", "Marinović");
		Student s10 = new Student("Ankica", "Lucić");
		
		g1.addStudent(s1);
		g1.addStudent(s2);
		g1.addStudent(s3);
		g1.addStudent(s4);
		g2.addStudent(s5);
		g2.addStudent(s6);
		g2.addStudent(s7);
		g2.addStudent(s8);
		g3.addStudent(s9);
		g3.addStudent(s10);
		
		List<Student> sh1 = new ArrayList<>();
		List<Student> sh2 = new ArrayList<>();
		sh1.add(s1);
		sh1.add(s2);
		sh1.add(s3);
		sh2.add(s1);
		sh2.add(s7);
		sh2.add(s8);
		Set<Student> sl1 = new HashSet<>();
		sl1.addAll(sh1);
		sl1.retainAll(sh2);
		System.out.println(sl1.size());
		
		//grupe.stream().filter(grupa -> grupa.naziv.contains("ć")).forEach(System.out::println);

/*
		grupe.stream()
			// zelim one grupe koje sadrze barem jednog studenta kojem ime sadrzi M
			 .anyMatch(grupa -> grupa.studenti)
			 .filter(grupa -> grupa.studenti.stream()
					 						.filter(student -> student.ime.contains("M")))
			 								.forEach(System.out::println);
			 .collect(Collectors.toList());
*/
		/*
		grupe.stream()
			 .filter(grupa -> grupa.studenti.stream()
					 						 .anyMatch(student -> student.ime.contains("Iv")))
			 .forEach(System.out::println);
			 //.collect(Collectors.toList());*/
		
		/*
		List<Grupa> gr = grupe.stream()
			 .map(grupa -> {
				 List<Student> g = grupa.studenti.stream()
						 					   .filter(student -> student.ime.contains("M"))
						 					   .collect(Collectors.toList());
				 return g;
			 })
			 .collect(Collectors.toList());
		System.out.println(grupa);
		*/
		

		/*
		System.out.println("Kristina".contains("Kat"));
		System.out.println("Ankica".contains("Kat"));
		System.out.println("Kristina".contains("K"));
		*/
		
		
		List<Grupa> res = new ArrayList<>();
		for (Grupa g : grupe) {
			Set<Student> studSet = g.studenti;
			for(Student s : studSet) {
				if (s.ime.contains("Mi")) {
					res.add(g);
					break;
				}
			}
		}
		/*
		System.out.println("================");
		for (Grupa g : res) {
			System.out.println(g);
		}
		*/
		List<Grupa> g = grupe.stream()
		 .filter(grupa -> grupa.studenti.stream()
				 						 .anyMatch(student -> student.ime.contains("Mi")))
		 //.forEach(System.out::println);
		 .collect(Collectors.toList());

	}
	
	private static class Student {
		private String ime;
		private String prezime;
		public Student(String ime, String prezime) {
			this.ime = ime;
			this.prezime = prezime;
		}
		@Override
		public String toString() {
			return " [" + ime + ", " + prezime + "]";
		}
		
	}
	
	private static class Grupa {
		private String naziv;
		private Set<Student> studenti;
		public Grupa(String naziv) {
			this.naziv = naziv;
			this.studenti = new HashSet<>();
		}
		public void addStudent(Student student) {
			this.studenti.add(student);
		}
		@Override
		public String toString() {
			return "{" + naziv + ", " + studenti + "}";
		}
		
	}

}
