/* Autore: DomenicoProfumo Profumo Corso B Matricola 533695 */

import java.util.Iterator;

public class main<E> {
	
	public static <E> void main(String args[]) {
		
		//creo i miei DATAContainer
		MySecureDataContainer<E> container1=new  MySecureDataContainer<E>();
		MySecureDataContainer2<E> container2=new  MySecureDataContainer2<E>();
		
		//eseguo il test 
		test(container1, "container1");
		test(container2, "container2");
		
		//eseguo il test per le eccezioni
		testException(container1, "container1");
		testException(container2, "container2");
		
	 }
	
	public static <E>void test(SecureDataContainer<E> x, String name) {
		System.out.println("\nEseguo test "+ name +":");
		
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) new Integer(5);
				E dato6=(E)	new Integer(6);	
				E dato7=(E) new String("Dato7");
				E dato8=(E) new String("Dato8");
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
					x.createUser("FrancescaLevi", "Levi");
					x.createUser("GiuseppeFerrero", "Ferrero");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					x.put("FrancescaLevi", "Levi",dato5);
					x.put("FrancescaLevi", "Levi",dato6);
					x.put("GiuseppeFerrero", "Ferrero",dato7);
					x.put("GiuseppeFerrero", "Ferrero",dato8);
				
					//stampo il numero di elementi presenti nella collezione di ciascun utente
					System.out.println("Utente DomenicoProfumo contiene " + x.getSize("DomenicoProfumo", "Profumo") + " dati");
					System.out.println("Utente MarioRossi contiene " +x.getSize("MarioRossi", "Rossi") + " dati");
					System.out.println("Utente FrancescaLevi contiene " +x.getSize("FrancescaLevi", "Levi") + " dati");
					System.out.println("Utente GiuseppeFerrero contiene " +x.getSize("GiuseppeFerrero", "Ferrero") + " dati");
				
					//DomenicoProfumo condivide il dato con GiuseppeFerrero
					x.share("DomenicoProfumo", "Profumo", "GiuseppeFerrero", dato2);
		
					//Ottengo una copia di tutti i dati degli utenti
					System.out.println("Recupero il dato1 dell'utente DomenicoProfumo:" + x.get("DomenicoProfumo", "Profumo",dato1));
					System.out.println("Recupero il dato2 dell'utente DomenicoProfumo:" +x.get("DomenicoProfumo", "Profumo",dato2));
					System.out.println("Recupero il dato1 dell'utente MarioRossi:" +x.get("MarioRossi", "Rossi",dato3));
					System.out.println("Recupero il dato2 dell'utente MarioRossi:" +x.get("MarioRossi", "Rossi",dato4));
					System.out.println("Recupero il dato1 dell'utente FrancescaLevi:"+x.get("FrancescaLevi", "Levi",dato5));
					System.out.println("Recupero il dato2 dell'utente FrancescaLevi:"+x.get("FrancescaLevi", "Levi",dato6));
					System.out.println("Recupero il dato1 dell'utente GiuseppeFerrero:"+x.get("GiuseppeFerrero", "Ferrero",dato7));
					System.out.println("Recupero il dato2 dell'utente GiuseppeFerrero:"+x.get("GiuseppeFerrero", "Ferrero",dato8));
				
					//MarioRossi fa una copia del dato4
					x.copy("MarioRossi", "Rossi", dato4);
				
					//controllo la nuova dimensione della copia per verificare la copia del dato
					System.out.println("Utente MarioRossi contiene " +x.getSize("MarioRossi", "Rossi") + " dati");
				
					//Test iteratore su MarioRossi
					Iterator<E> k=x.getIterator("MarioRossi", "Rossi");
				
					System.out.print("Lista dati Utente MarioRossi:");
					
					while(k.hasNext()) {
						System.out.print(" " + k.next());
					}
				
					//cancello i dati di tutti gli utenti
					x.remove("DomenicoProfumo", "Profumo",dato1);
					x.remove("DomenicoProfumo", "Profumo",dato2);
					x.remove("MarioRossi", "Rossi",dato3);
					x.remove("MarioRossi", "Rossi",dato4);
					x.remove("MarioRossi", "Rossi",dato4);			//il dato copiato
					x.remove("FrancescaLevi", "Levi",dato5);
					x.remove("FrancescaLevi", "Levi",dato6);
					x.remove("GiuseppeFerrero", "Ferrero",dato7);
					x.remove("GiuseppeFerrero", "Ferrero",dato8);
					x.remove("GiuseppeFerrero", "Ferrero",dato2);		//il dato condiviso
					
					//stampo il numero di elementi presenti nella collezione di ciascun utente
					System.out.println("\nUtente DomenicoProfumo contiene " + x.getSize("DomenicoProfumo", "Profumo") + " dati");
					System.out.println("Utente MarioRossi contiene " +x.getSize("MarioRossi", "Rossi") + " dati");
					System.out.println("Utente FrancescaLevi contiene " +x.getSize("FrancescaLevi", "Levi") + " dati");
					System.out.println("Utente GiuseppeFerrero contiene " +x.getSize("GiuseppeFerrero", "Ferrero") + " dati");
					
					//cancello gli utenti
					x.removeUser("DomenicoProfumo", "Profumo");
					x.removeUser("MarioRossi", "Rossi");
					x.removeUser("FrancescaLevi", "Levi");
					x.removeUser("GiuseppeFerrero", "Ferrero");
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id o password=null");
				}
	}
	
	public static <E>void testException(SecureDataContainer<E> x, String name) {
		System.out.println("\nEseguo testException "+ name +":");

		Exception1(x);
		Exception2(x);
		Exception3(x);
		Exception4(x);
		Exception5(x);
		Exception6(x);
		Exception7(x);
		Exception8(x);
	}
	
	//test inserimento dato==null
	public static <E>void Exception1(SecureDataContainer<E> x) {
		System.out.print("Exception1: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					
					//provo ad inserire un dato==null
					x.put("DomenicoProfumo", "Profumo",dato5);
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}
	}
	//test inserimento dato a utente non registrato
	public static <E>void Exception2(SecureDataContainer<E> x) {
		System.out.print("Exception2: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					
					//provo ad inserire il dato ad un utente non registrato
					x.put("GiuseppeFerrero", "Ferrero",dato5);
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}
	}
	//test numero di elementi di un utente non registrato
	public static <E>void Exception3(SecureDataContainer<E> x) {
		System.out.print("Exception3: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					
					//stampo il numero di elementi presenti nella collezione di un utente non registrato
					System.out.println("Utente GiuseppeFerrero contiene " +x.getSize("GiuseppeFerrero", "Ferrero") + " dati");
			
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}	
	}
	//test condivisione dato con utente non registrato
	public static <E>void Exception4(SecureDataContainer<E> x) {
		System.out.print("Exception4: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					
					//DomenicoProfumo condivide il dato con GiuseppeFerrero(non registrato)
					x.share("DomenicoProfumo", "Profumo", "Roberto", dato1);
				
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}	
	}
	//test copia dato che non appartiene all'utente
	public static <E>void Exception5(SecureDataContainer<E> x) {
		System.out.print("Exception5: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
					
					//MarioRossi fa una copia di un dato che non gli appartiene
					x.copy("MarioRossi", "Rossi", dato1);
				
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}	
	}
	//test iteratore su utente non registrato
	public static <E>void Exception6(SecureDataContainer<E> x) {
		System.out.print("Exception6: ");
		//creo un pò di dati da salvare
				E dato1=(E) new String("Dato1");
				E dato2=(E) new Integer(2);
				E dato3=(E) new String("Dato3");
				E dato4=(E) new String("Dato4");
				E dato5=(E) null;
				
				try {
					//registro un pò di utenti
					x.createUser("DomenicoProfumo", "Profumo");
					x.createUser("MarioRossi", "Rossi");
			
					//inserisco i dati 
					x.put("DomenicoProfumo", "Profumo",dato1);
					x.put("DomenicoProfumo", "Profumo",dato2);
					x.put("MarioRossi", "Rossi",dato3);
					x.put("MarioRossi", "Rossi",dato4);
				
					//Test iteratore su utente non registrato
					Iterator<E> k=x.getIterator("MarioRossi", "Ferraro");
				
					System.out.print("Lista dati Utente MarioRossi:");
					
					while(k.hasNext()) {
						System.out.print(" " + k.next());
					}
					
				}
				
				catch(InvalidArgumentException e) {
					System.out.println("utente non registrato o password non corretta");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Dato non trovato");
				}
				catch (NullPointerException e) {
					System.out.println("Id,password o data =null");
				}	
	}
	//test cancellazione utente non registrato
	public static <E>void Exception7(SecureDataContainer<E> x){ 
		System.out.print("Exception7: ");
		//creo un pò di dati da salvare
			E dato1=(E) new String("Dato1");
			E dato2=(E) new Integer(2);
			E dato3=(E) new String("Dato3");
			E dato4=(E) new String("Dato4");
			E dato5=(E) null;
			
			try {
				//registro un pò di utenti
				x.createUser("DomenicoProfumo", "Profumo");
				x.createUser("MarioRossi", "Rossi");
		
				//inserisco i dati 
				x.put("DomenicoProfumo", "Profumo",dato1);
				x.put("DomenicoProfumo", "Profumo",dato2);
				x.put("MarioRossi", "Rossi",dato3);
				x.put("MarioRossi", "Rossi",dato4);
				
				//cancello un utente non registrato
				x.removeUser("GiuseppeFerrero", "Ferrero");
			}
			
			catch(InvalidArgumentException e) {
				System.out.println("utente non registrato o password non corretta");
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("Dato non trovato");
			}
			catch (NullPointerException e) {
				System.out.println("Id,password o data =null");
			}	
	}
	//test password non corretta
	public static <E>void Exception8(SecureDataContainer<E> x){
		System.out.print("Exception8: ");
		//creo un pò di dati da salvare
		E dato1=(E) new String("Dato1");
		E dato2=(E) new Integer(2);
		E dato3=(E) new String("Dato3");
		E dato4=(E) new String("Dato4");
		E dato5=(E) null;
		
		try {
			//registro un pò di utenti
			x.createUser("DomenicoProfumo", "Profumo");
			x.createUser("MarioRossi", "Rossi");
	
			//inserisco i dati 
			x.put("DomenicoProfumo", "Profumo",dato1);
			//inserisco i dati con password diversa
			x.put("DomenicoProfumo", "Rossi",dato2);
			x.put("MarioRossi", "Rossi",dato3);
			x.put("MarioRossi", "Rossi",dato4);
		
		}
		
		catch(InvalidArgumentException e) {
			System.out.println("utente non registrato o password non corretta");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Dato non trovato");
		}
		catch (NullPointerException e) {
			System.out.println("Id,password o data =null");
		}	
	}
}

