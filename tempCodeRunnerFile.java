	private Set<Transicion> transiciones;
	private String estadoInicial;
	private Set<String> estadosFinales;
	
	public AFN() {
		transiciones = new HashSet<>(); //conjunto de transiciones
		estadosFinales = new HashSet<>(); //conjunto de estados finales
	}
	