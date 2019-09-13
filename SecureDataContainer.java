/* Autore: Domenico Profumo Corso B Matricola 533695 */

import java.util.Iterator;

public interface SecureDataContainer<E> {
	//OVERVIEW: Tipo modificabile che contiene oggetti di tipo E con dominio K e codominio V,
	//			definite solo su un sotto-insieme finito di K.
    //Typical Element: una funzione parziale f: K -> V per la quale { k | f(k) definito }
    //				   e' un insieme finito	
	
	// Crea l’identità un nuovo utente della collezione
	public void createUser(String Id, String passw) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Id !=null && passw!=null && non esiste una coppia <Id, passw> in this
    //THROWS: Se Id=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se esiste una coppia <Id, passw> in this throws InvalidArgumentException (unchecked, non disponibile in java)
    //MODIFIES: this
    //EFFECTS: inserisce la coppia (nome utente, struttura utente) dove la struttura utente
	//		   contiene (Id,passw,lista dati associati)
	
	// Restituisce il numero degli elementi di un utente presenti nella
	// collezione
	public int getSize(String Owner, String passw) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null o passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce il numero degli elementi associati all'utente Owner se >0, 0 altrimenti
	
	// Inserisce il valore del dato nella collezione
	// se vengono rispettati i controlli di identità
	public boolean put(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES:this
    //EFFECTS: inserisce il dato nella collezione associata all'utente Owner, restituisce true
	//			in caso di successo, false in caso di fallimento
	
	// Ottiene una copia del valore del dato nella collezione
	// se vengono rispettati i controlli di identità
	public E get(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce una copia del dato presente nella collezione associata all'utente Owner, null altrimenti
	
	// Rimuove il dato nella collezione
	// se vengono rispettati i controlli di identità
	public E remove(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES:this
    //EFFECTS: restituisce il dato rimosso, null altrimenti
	
	// Crea una copia del dato nella collezione
	// se vengono rispettati i controlli di identità
	public void copy(String Owner, String passw, E data) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: crea una copia del dato nella collezione dell'utente Owner inserendola in fondo 
	
	// Condivide il dato nella collezione con un altro utente
	// se vengono rispettati i controlli di identità
	public void share(String Owner, String passw, String Other, E data) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && Other!=null && data!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null, Other=null o data=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//MODIFIES: this
    //EFFECTS: crea una copia del dato della collezione dell'utente Owner e lo inserisce 
	//		   nella collezione dell'utente Other

	// restituisce un iteratore (senza remove) che genera tutti i dati
	//dell’utente in ordine arbitrario
	// se vengono rispettati i controlli di identità
	public Iterator<E> getIterator(String Owner, String passw) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: restituisce un iteratore che genera tutti i dati dell'utente, null altrimenti
	
	// … altre operazione da definire a scelta
	
	//rimuove l'utente
	public void removeUser(String Owner, String passw) throws InvalidArgumentException, NullPointerException;
	//REQUIRES: Id!=null && passw!=null && esiste una coppia <Owner, passw> in this
    //THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
    //EFFECTS: rimuove l'utente dalla collezione
	
	//verifica corretta autenticazione
	public boolean Autentication(String Owner, String passw) throws NullPointerException, InvalidArgumentException;
	//REQUIRES: Owner !=null && passw!=null && esiste una coppia <Owner, passw> in this
	//THROWS: Se Owner=null, passw=null throws NullPointerException (unchecked)
	//THROWS: Se non esiste una coppia <Owner, passw> in this throws InvalidArgumentException  (unchecked, non disponibile in java)
	//EFFECTS: restituisce true se la password di Owner è corretta e se l'utente è registrato, false altrimenti
}
