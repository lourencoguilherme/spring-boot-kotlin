package com.guimtlo.digitalinovationone.repositories

import com.guimtlo.digitalinovationone.model.entity.Jedi
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JediRepository : JpaRepository<Jedi, Long>