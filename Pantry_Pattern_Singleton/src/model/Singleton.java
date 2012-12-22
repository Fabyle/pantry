package model;

public class Singleton
{		
	/** Constructeur privé */	
	private Singleton()
	{}
 
	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
		private final static Singleton instance = new Singleton();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Singleton getInstance()
	{
		return SingletonHolder.instance;
	}
}
