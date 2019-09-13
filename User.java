/* Autore: Domenico Profumo Corso B Matricola 533695 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

 class User<E>{
	// Overview:Tipo modificabile contenente la struttura di ciascun utente
    // Typical Element: <user,password, data_0 + ... + data_{size()-1}> con size() <= dim
	// AF = <user_name, user_password, data.get(0) + ... + data.get(dim-1)]> con 0<=data.get(i)<=dim
    // IR =user_name != null && user_password!=null && 0 <= dim <= data.size
	//		&& data.get(i)!=null
    //      && dim = #{ i | data.get(i) != null }
	
	private String user;							//nome utente
	private String password;						//password
	private ArrayList<E> datalist;					//lista dati associati
	private int dim;								//numero dati
	
	//crea un nuovo utente
	public User(String user, String password) {
		this.user=user;
		this.password=password;
		this.datalist=new ArrayList<E>();
		this.dim=0;
	}
	
	//controlla l'identità(user,password) dell'utente
	public boolean controll(String user, String password) {
		//REQUIRES: user!=null && passw!=null
		//THROWS: Se user=null, password=null throws NullPointerException (unchecked)
	    //EFFECTS: restituisce true se la password e il nome utente coincidono
		if(this.user==user && this.password==password) return true;
		return false;
	}
	
	//inserisce il dato
	public void insert(E data) {
		//REQUIRES: data!=null
		//THROWS: Se data=null throws NullPointerException (unchecked)
		//MODIFIES:this
	    //EFFECTS:aggiunge il dato in datalist
		datalist.add(data);
		dim++;
	}
	
	//cancella il dato
	public boolean remove(E data) {
		//REQUIRES:	data!=null
		//THROWS: Se data=null throws NullPointerException (unchecked)
		//MODIFIES:this
	    //EFFECTS: cancella il dato dalla collezione, se presente restituisce true, false altrimenti
		if(datalist.remove(data)) {
			dim--;
			return true;
		}
		return false;
	}
	
	//restituisce il dato
	public E get(E data) throws IndexOutOfBoundsException {
		//REQUIRES:	data!=null && data appartiene alla collezione, lancia un eccezione altrimenti
		//THROWS: Se data non appartiene alla collezione lancia IndexOutOfBoundsException
		//THROWS: Se data=null throws NullPointerException (unchecked)
		//EFFECTS: restituisce una copia del dato se presente, null altrimenti
			E dat;
			int i=datalist.indexOf(data);
			if(0<=i && i<=dim) {
				dat=datalist.get(i);
				return dat;
			}
			else throw new IndexOutOfBoundsException("dato non trovato!");
	}
	
	//controlla se data è presente
	public boolean exist(E data) {
		//REQUIRES: data!=null
		//THROWS: Se data=null throws NullPointerException (unchecked)
		//EFFECTS: restituisce true se il dato è presente nella collezione, false altrimenti
		return datalist.contains(data);
	}
	
	//restituisce un iteratore sulla lista dei dati
	public Iterator<E> Iterator() {
	    //EFFECTS:restituisce un iteratore (senza remove) che genera tutti i dati
		// dell’utente in ordine arbitrario
		return Collections.unmodifiableList(datalist).iterator();
	}
	
	//restituisce il nome
	public String getName () {
	    //EFFECTS: restituisce il nome dell'utente
	   return user;
	}
	
	//restituisce il numero di dati presenti nella collezione
	public int sizedatalist() {
	    //EFFECTS: restitusce la dimensione della lista dati
		return dim;
	}
	
	
}
