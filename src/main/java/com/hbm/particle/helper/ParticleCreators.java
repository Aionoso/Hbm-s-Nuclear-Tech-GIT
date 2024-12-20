package com.hbm.particle.helper;

import java.util.HashMap;

public class ParticleCreators {

    public static HashMap<String, IParticleCreator> particleCreators = new HashMap();

    static {
        particleCreators.put("casingNT", new CasingCreator());
    }
}
