/* Autore: Domenico Profumo Corso B Matricola 533695 */

import java.util.HashMap;
import java.util.Iterator;

public class MySecureDataContainer<E> implements SecureDataContainer<E> {
	//OVERVIEW: Tipo modificabile che contiene oggetti di tipo E con dominio K e codominio V,
	//			definite solo su un sotto-insieme finito di K.
	// AF = f: K -> V tale che f(keys.get(i)) = values.get(i) per 0 <= i < dim, e
    //      indefinito altrimenti dove keys=user_name  e values=<user_name,user_password,user_data>
    // IR = keys != null && values!=null && keys.size() = values.size() = dim
    //      && for all i. 0 <= i < dim ==> ((keys.get(i) = values.get(i).getname()) &&
    //                                      (values.get(i) != null) &&
    //      && for all i != j. 0 <= i, j < dim ==> (keys.get(i) != keys.get(j))
	
	private HashMap<String, User<E>> lista;			//struttura hash
	private int dim;								//dim=numero utenti registrati
	
	public MySecureDataContainer() {
		//costruttore, crea l'hashmap da usare
		dim=0;
		lista=null;
		lista= new HashMap<String,User<E>>();
	}

	// Crea l’identità un nuovo utente della collezione
	public void createUser(String Id, String passw) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Id !=null && passw!=null && non esiste una coppia <Id, passw> in this
    //THROWS: Se Id=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se esiste una coppia <Id, passw> in this throws InvalidArgumentException (unchecked, non disponibile in java)
    //MODIFIES: this
    //EFFECTS: inserisce la coppia (nome utente, struttura utente) dove la struttura utente
	//		   contiene (Id,passw,lista dati associati)

	       if (Id==null || passw==null) throw new  NullPointerException("Id o password ==null");
	      
	       //controllo se il nome utente è già registrato, se non lo è inserisco l'utente
			if(lista.containsKey(Id)) throw new  InvalidArgumentException("Id registato");
			lista.put(Id, new User<E>(Id,passw));
			dim++;
	}

	// Restituisce il numero degli elementi di un utente presenti nella
	// collezione
	public int getSize(String Owner, String passw) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: restituisce il numero degli elementi associati all'utente Owner se >0, 0 altrimenti
		
	   //controllo se il nome utente è già registrato, se lo è recupero il numero di dati associati 
		if(Autentication(Owner, passw)) {
			return lista.get(Owner).sizedatalist();
		}
		else throw new  InvalidArgumentException("Id non registato");

	}

	// Inserisce il valore del dato nella collezione
	// se vengono rispettati i controlli di identità	
	public boolean put(String Owner, String passw, E data) throws InvalidArgumentException,NullPointerException {
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
	    	 User<E> x= lista.get(Owner);
	    	 x.insert(data);
	    	 return true;
	     }
	    
	  return false;
		
	}

	// Ottiene una copia del valore del dato nella collezione
	// se vengono rispettati i controlli di identità
	public E get(String Owner, String passw, E data) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: restituisce una copia del dato presente nella collezione associata all'utente Owner, null altrimenti
		 
		if (data==null) throw new  NullPointerException("data ==null");

	    //controllo se il nome utente è già registrato e se la password è corretta, se lo è
		//ritorno una copia del dato 
		 if(Autentication(Owner, passw)) {
	    	User<E> x= lista.get(Owner);
	    	return x.get(data);
	     }

	 return null;	
	}

	// Rimuove il dato nella collezione
	// se vengono rispettati i controlli di identità
	public E remove(String Owner, String passw, E data) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES:this
    //EFFECTS: restituisce il dato rimosso, null altrimenti

		if (data==null) throw new  NullPointerException("data ==null");
		 
	     //controllo se il nome utente è già registrato, se lo è faccio i controlli di user e password
		 //ed elimino il dato se presente,restituisco null altrimenti
		 if(Autentication(Owner, passw)) {
	    	 User<E> x= lista.get(Owner);
	    		 if(x.remove(data)) return data;
	     }
	     
	   return null;
	}

	// Crea una copia del dato nella collezione
	// se vengono rispettati i controlli di identità
	public void copy(String Owner, String passw, E data) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: crea una copia del dato nella collezione dell'utente Owner inserendola in fondo 
		
		if (data==null) throw new  NullPointerException("data ==null");
	      
	    //controllo se il nome utente è già registrato,  se lo è faccio i controlli di user e password
		//e lo inserisco in fondo alla lista
		 if(Autentication(Owner, passw)) {
	    	User<E> x= lista.get(Owner);
	    	x.insert(data);
	     }
	     else throw new InvalidArgumentException("utente non registrato");

	}

	// Condivide il dato nella collezione con un altro utente
	// se vengono rispettati i controlli di identità
	public void share(String Owner, String passw, String Other, E data) throws InvalidArgumentException,NullPointerException {
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
			User<E> x= lista.get(Owner);
				if(x.exist(data)==false) {
					 x.insert(data);
				}
		}
		
		if(lista.containsKey(Other)) {
			User<E> y= lista.get(Other);
			if(y.exist(data)==false) {
			 y.insert(data);
			}
		}
		else throw new InvalidArgumentException("Other non registrato");

	}

	// restituisce un iteratore (senza remove) che genera tutti i dati
	//dell’utente in ordine arbitrario
	// se vengono rispettati i controlli di identità
	public Iterator<E> getIterator(String Owner, String passw) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: restituisce un iteratore che genera tutti i dati dell'utente, null altrimenti
		     
		//controllo se il nome utente è già registrato
		if(Autentication(Owner, passw)) {   
			User<E> x= lista.get(Owner);
				return x.Iterator();
		}

		return null;
	}

	//rimuove l'utente
	public void removeUser(String Owner, String passw) throws InvalidArgumentException,NullPointerException {
	//REQUIRES: Id!=null && passw!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: rimuove l'utente dalla collezione
				
		if(Autentication(Owner, passw)) {
				lista.remove(Owner);
				dim--;
		}

	}

	//verifica corretta autenticazione
	public boolean Autentication(String Owner, String passw) throws NullPointerException, InvalidArgumentException {
		//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
		//THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
		//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
		//EFFECTS: restituisce true se la password di Owner è corretta e se l'utente è registrato, false altrimenti

		if (Owner==null || passw==null ) throw new  NullPointerException("Id o password==null");
	
		if(lista.containsKey(Owner)) {
			User<E> x= lista.get(Owner);
			if(x.controll(Owner, passw)) {
				return true;
			}
		}
		else throw new InvalidArgumentException("utente non registrato o password non corretta");
		return false;
	}
} 

class InvalidArgumentException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException(String s) {
        super(s);
    }
}
