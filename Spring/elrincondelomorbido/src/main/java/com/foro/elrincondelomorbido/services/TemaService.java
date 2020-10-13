package com.foro.elrincondelomorbido.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.foro.elrincondelomorbido.Serializer.HidableSerializer;
import com.foro.elrincondelomorbido.models.Comentario;
import com.foro.elrincondelomorbido.models.Foro;
import com.foro.elrincondelomorbido.models.Hidable;
import com.foro.elrincondelomorbido.models.Tema;
import com.foro.elrincondelomorbido.models.Usuario;
import com.foro.elrincondelomorbido.repositories.TemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaService {
    @Autowired
    TemaRepository temaRepository;
    @Autowired
    ForoService foroService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ComentarioService comentarioService;

    public void crearTema(String nombre_foro, Long id_usuario, Tema tema) {
        Foro foro = foroService.obtenerForo(nombre_foro);
        Usuario usuario = usuarioService.obtenerUsuario(id_usuario);
        tema.setForo(foro);
        tema.setUsuario(usuario);
        tema.setFechaPublicacion(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        tema.setRanking(0);
        if (foro.isModerado())
            tema.setAprobado(false);
        else
            tema.setAprobado(true);
        temaRepository.save(tema);
        foroService.agregarTema(nombre_foro, tema);
        usuarioService.agregarTema(id_usuario, tema);
    }

    public Tema obtenerTema(Long id) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.registerModule(new SimpleModule() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setupModule(final SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(final SerializationConfig config,
                            final BeanDescription beanDesc, final JsonSerializer<?> serializer) {
                        if (Hidable.class.isAssignableFrom(beanDesc.getBeanClass())) {
                            return new HidableSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });
        try {
            return mapper.readValue(mapper.writeValueAsString(temaRepository.findById(id).orElseThrow()), Tema.class) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tema obtenerTemaAdmin(Long id) {
		return temaRepository.findById(id).orElseThrow();
    }
    
    public Iterable<Tema> temasAdmin(String nombre_foro) {
		return foroService.obtenerTemasAdmin(nombre_foro);
	}

	public Iterable<Tema> temas(String nombre_foro) {
		return foroService.obtenerTemas(nombre_foro);
	}

	public void puntuarTemaPositivo(Long id) {
        Tema tema = temaRepository.findById(id).orElseThrow();
        tema.setRanking(tema.getRanking()+1);
        temaRepository.save(tema);
	}

	public void puntuarTemaNegativo(Long id) {
        Tema tema = temaRepository.findById(id).orElseThrow();
        tema.setRanking(tema.getRanking()-1);
        temaRepository.save(tema);
    }
    
    public void agregarComentario(Long id, Comentario comentario){
        Tema tema = temaRepository.findById(id).orElseThrow();
        tema.addComentario(comentario);
        temaRepository.save(tema);
    }

    public void aprobarTema(Long id) {
        Tema tema = temaRepository.findById(id).orElseThrow();
        tema.setAprobado(true);
        temaRepository.save(tema);
	}

	public void desaprobarTema(Long id) {
        temaRepository.deleteById(id);
	}
}
