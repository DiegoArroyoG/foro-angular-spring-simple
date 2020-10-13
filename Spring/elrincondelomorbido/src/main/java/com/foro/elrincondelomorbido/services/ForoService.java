package com.foro.elrincondelomorbido.services;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.foro.elrincondelomorbido.Serializer.HidableSerializer;
import com.foro.elrincondelomorbido.models.Foro;
import com.foro.elrincondelomorbido.models.Hidable;
import com.foro.elrincondelomorbido.models.Tema;
import com.foro.elrincondelomorbido.repositories.ForoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForoService {
    @Autowired
    ForoRepository foroRepository;

    public Iterable<Foro> foros(){
        return foroRepository.findAll();
    }

    public void crearForo(Foro foro){
        foroRepository.save(foro);
    }

    public void agregarTema(String nombre_foro, Tema tema){
        Foro foro = foroRepository.findById(nombre_foro).orElseThrow();
        foro.addTemas(tema);
        foroRepository.save(foro);
    }

    public List<Tema> obtenerTemasAdmin(String nombre_foro){
        Foro foro = foroRepository.findById(nombre_foro).orElseThrow();
        return foro.getTemas();
    }

    public List<Tema> obtenerTemas(String nombre_foro){
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
            Foro foro = foroRepository.findById(nombre_foro).orElseThrow();
            return Arrays.asList(mapper.readValue(mapper.writeValueAsString(foro.getTemas()), Tema[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Foro obtenerForo(String nombre_foro){
        return foroRepository.findById(nombre_foro).orElseThrow();
    }
}
