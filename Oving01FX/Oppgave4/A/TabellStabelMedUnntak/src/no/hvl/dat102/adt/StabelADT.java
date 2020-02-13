package no.hvl.dat102.adt;

import no.hvl.dat102.EmptyCollectionException;

public interface StabelADT<T> {
 
/*
* Definerer interface til en datastruktur stabel.
//****************************************************************
*/
  /**
   * Legger til et element p� toppen av stabelen, utvider hvis behov
 * @param element elementet som stables p�
 */
public void push (T element);
  

/**
 * Fjerner et element p� toppen av stabelen og returnerer referansen
 * Kaster unntak EmptyCollectionException hvis stabelen allerede er tom.
 * @return T element fjernes fra toppen av stabelen
 * @exception EmptyCollectionException kastes hvis stabelen allerde er tom
 */
public T pop();

/** 
/**
 * Returnerer referansen til elementet p� toppen av stabelen.
 * Elementet blir ikke fjernet.
  * @return T element p� toppen av stabelen.
  * @exception  EmptyCollectionException kastes hvis stabelen allerede er tom.
 * 
 * 
 */
public T peek() ;


   
  /** 
   *  Bestemmer om stabelen er full.
 * @return sann hvis tom stabel
 */
public boolean erTom();


  /**
   * Returnerer antall elementer p� stabelen.
 * @return antall elementer p� stabelen
 */
public int antall();
  
}

