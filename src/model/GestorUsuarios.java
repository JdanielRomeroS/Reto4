package model;

import java.util.List;

public class GestorUsuarios {

		List<User> listaUsuarios;

		public GestorUsuarios(List<User> listaUsuarios) {
			this.listaUsuarios = listaUsuarios;
		}

		public List<User> getListaUsuarios() {
			return listaUsuarios;
		}

		public void setListaUsuarios(List<User> listaUsuarios) {
			this.listaUsuarios = listaUsuarios;
		}
		
}
