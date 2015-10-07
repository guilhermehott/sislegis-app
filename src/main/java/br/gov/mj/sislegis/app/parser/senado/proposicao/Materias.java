package br.gov.mj.sislegis.app.parser.senado.proposicao;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Materias")
class Materias {
	@XStreamImplicit(itemFieldName = "Materia")
	List<Materia> materias;

	static void configXstream(XStream xstream) {
		xstream.processAnnotations(Materias.class);
		Materia.configXstream(xstream);

	}
}