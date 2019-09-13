/* Autore: Domenico Profumo Corso B Matricola 533695 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySecureDataContainer2<E> implements SecureDataContainer<E> {
    // Overview: Tipo modificabile che contiene oggetti di tipo E con dominio K e codominio V,
	//			definite solo su un sotto-insieme finito di K.
    // AF = <user_name.get(i),user_list.get(i)> con  0<=dim<user_name.size, e
    //      indefinito altrimenti
    // IR = user_name != null && user_list != null && dim=user_name.size=user_list.size
    //      && for all i. 0 <= i < dim ==> (( user_name.get(i) != null) &&
    //                                      (values.get(i) != null) &&
    //                                      (user_name.get(i) = user_list.get(i).getName()))
    //      && for all i != j. 0 <= i, j < dim ==> (user_name.get(i) != user_name.get(j)) && (user_list.get(i)!=user_list.get(j))
    
	private int dim;
    private List<String> user_name;
    private List<User<E>> user_list;
    
    public MySecureDataContainer2() {
    	this.dim=0;
		user_name= new ArrayList<String>();
		user_list= new ArrayList<User<E>>();
	}

	// Crea l’identità un nuovo utente della collezione
	public void createUser(String Id, String passw) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Id !=null && passw!=null && non esiste una coppia <Id, passw> in this
    //THROWS: Se Id=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se esiste una coppia <Id, passw> in this throws InvalidArgumentException (unchecked, non disponibile in java)
    //MODIFIES: this
    //EFFECTS: inserisce la coppia (nome utente, struttura utente) dove la struttura utente
	//		   contiene (Id,passw,lista dati associati)

	       if (Id==null || passw==null) throw new  NullPointerException("Id o password ==null");
	      
	       //controllo se il nome utente è già registrato, se non lo è inserisco l'utente
	       if(user_name.contains(Id)) throw new  InvalidArgumentException("Id registato");
			user_list.add(new User<E>(Id,passw));
			user_name.add(Id);
			dim++;
	}

	// Restituisce il numero degli elementi di un utente presenti nella
	// collezione
	public int getSize(String Owner, String passw) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce il numero degli elementi associati all'utente Owner se >0, 0 altrimenti

	   //controllo se il nome utente è già registrato, se lo è recupero il numero di dati associati 
		if(Autentication(Owner, passw)) {
			int index=user_name.indexOf(Owner);
			return user_list.get(index).sizedatalist();
		}
		return 0;

	}

	// Inserisce il valore del dato nella collezione
	// se vengono rispettati i controlli di identità	
	public boolean put(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES:this
    //EFFECTS: inserisce il dato nella collezione associata all'utente Owner, restituisce true
	//			in caso di successo, false in caso di fallimento

		 if (data==null) throw new  NullPointerException("data ==null");

	     //controllo se il nome utente è già registrato, se non lo è recupero la struttura utente, 
		 //controllo se la password è corretta e inserisco l'elemento 
		 if(Autentication(Owner, passw)) {
			 int index=user_name.indexOf(Owner);
			 User<E> x= user_list.get(index);
	    	 x.insert(data);
	    	 return true;
	     }
	    
	  return false;
		
	}

	// Ottiene una copia del valore del dato nella collezione
	// se vengono rispettati i controlli di identità
	public E get(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce una copia del dato presente nella collezione associata all'utente Owner, null altrimenti
		 
		if (data==null) throw new  NullPointerException("data ==null");

	    //controllo se il nome utente è già registrato e se la password è corretta, se lo è
		//ritorno una copia del dato 
		 if(Autentication(Owner, passw)) {
			 int index=user_name.indexOf(Owner);
	    	 User<E> x= user_list.get(index);
	    	return x.get(data);
	     }

	 return null;	
	}

	// Rimuove il dato nella collezione
	// se vengono rispettati i controlli di identità
	public E remove(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES:this
    //EFFECTS: restituisce il dato rimosso, null altrimenti
		if (data==null) throw new  NullPointerException("data ==null");
		 
	     //controllo se il nome utente è già registrato, se lo è faccio i controlli di user e password
		 //ed elimino il dato se presente,restituisco null altrimenti
		 if(Autentication(Owner, passw)) {
			 int index=user_name.indexOf(Owner);
	    	 User<E> x= user_list.get(index);
	    	 if(x.remove(data)) return data;
	     }
	     
	   return null;
	}

	// Crea una copia del dato nella collezione
	// se vengono rispettati i controlli di identità
	public void copy(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: crea una copia del dato nella collezione dell'utente Owner inserendola in fondo 
		if (data==null) throw new  NullPointerException("data ==null");
	      
	    //controllo se il nome utente è già registrato,  se lo è faccio i controlli di user e password
		//e lo inserisco in fondo alla lista
		 if(Autentication(Owner, passw)) {
			 int index=user_name.indexOf(Owner);
	    	 User<E> x= user_list.get(index);
	    	x.insert(data);
	     }
	     else throw new InvalidArgumentException("utente non registrato");

	}

	// Condivide il dato nella collezione con un altro utente
	// se vengono rispettati i controlli di identità
	public void share(String Owner, String passw, String Other, E data) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && Other!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null, Other=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES: this
    //EFFECTS: crea una copia del dato della collezione dell'utente Owner e lo inserisce 
	//		   nella collezione dell'utente Other
		if (data==null) throw new  NullPointerException("data ==null");
		if (Other==null ) throw new  NullPointerException("Other==null");
	      
		//controllo se Owner e Other sono già registrati, se lo sono,
		//inserisco il dato nelle collezioni di Other e Owner, se già esisteva non lo inserisco
		 if(Autentication(Owner, passw)) {
			 int index=user_name.indexOf(Owner);
	    	 User<E> x= user_list.get(index);
				if(x.exist(data)==false) {
					 x.insert(data);
				}
		}
			
		 if(user_name.contains(Other)) {
			 int index=user_name.indexOf(Other);
	    	 User<E> y= user_list.get(index);
				if(y.exist(data)==false) {
				 y.insert(data);
			}
			}


	}

	// restituisce un iteratore (senza remove) che genera tutti i dati
	//dell’utente in ordine arbitrario
	// se vengono rispettati i controlli di identità
	public Iterator<E> getIterator(String Owner, String passw) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce un iteratore che genera tutti i dati dell'utente, null altrimenti
	     
		//controllo se il nome utente è già registrato
		if(Autentication(Owner, passw)) {   
			 int index=user_name.indexOf(Owner);
	    	 User<E> x= user_list.get(index);
				return x.Iterator();
		}

		return null;
	}

	//rimuove l'utente
	public void removeUser(String Owner, String passw) throws InvalidArgumentException, NullPointerException{
	//REQUIRES: Owner!=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: rimuove l'utente dalla collezione
		
		if(Autentication(Owner, passw)) {
			int index=user_name.indexOf(Owner);
			user_list.remove(index);
			user_name.remove(index);
			dim--;
		}

	}

	//verifica corretta autenticazione
	public boolean Autentication(String Owner, String passw) throws NullPointerException, InvalidArgumentException{
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: restituisce true se la password di Owner è corretta e se l'utente è registrato, false altrimenti
		if (Owner==null || passw==null ) throw new  NullPointerException("Id o password==null");
	
		if(user_name.contains(Owner)) {
			int index=user_name.indexOf(Owner);
			if(user_list.get(index).controll(Owner, passw)) {
				return true;
			}
		}
		else throw new InvalidArgumentException("utente non registrato o password non corretta");
		return false;
	}
}
