package com.guimtlo.digitalinovationone.services

import com.guimtlo.digitalinovationone.exceptions.ErrorCode
import com.guimtlo.digitalinovationone.exceptions.ServiceException
import com.guimtlo.digitalinovationone.model.entity.Jedi
import com.guimtlo.digitalinovationone.repositories.JediRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JediService {

    @Autowired
    private lateinit var jediRepository: JediRepository

    fun createJedi(jedi: Jedi) : Jedi {
        return jediRepository.save(jedi)
    }

    fun findJediById(jediId: Long) : Jedi {
        return jediRepository.findById(jediId).orElse(throw ServiceException(ErrorCode.JEDI_BY_ID_NOT_FOUNT, listOf(jediId)))
    }
}