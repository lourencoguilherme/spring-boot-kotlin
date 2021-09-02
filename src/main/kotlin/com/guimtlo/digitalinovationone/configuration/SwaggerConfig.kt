package com.guimtlo.digitalinovationone.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Header
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {
    private val m201 = customMessage1()
    private val m204put = simpleMessage(204, "Atualização ok")
    private val m204del = simpleMessage(204, "Deleção ok")
    private val m400 = simpleMessage(400, "Pedido ruim")
    private val m403 = simpleMessage(403, "Não autorizado")
    private val m404 = simpleMessage(404, "Não encontrado")
    private val m422 = simpleMessage(422, "Erro de validação")
    private val m500 = simpleMessage(500, "Erro inesperado")

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, listOf(m400, m403, m404, m500))
            .globalResponseMessage(RequestMethod.POST, listOf(m400, m201, m403, m422, m500))
            .globalResponseMessage(RequestMethod.PUT, listOf(m400, m204put, m403, m404, m422, m500))
            .globalResponseMessage(RequestMethod.DELETE, listOf(m400, m204del, m403, m404, m500))
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.guimtlo.digitalinovationone.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfo(
            "Projeto pratico bootcamp digital inovation",
            "Essa Api é utilizada para aprendizado",
            "Versão 1.0",
            "",
            Contact(
                "Guilherme Lourenco",
                "https://github.com/lourencoguilherme",
                "guilhermemonteirolourenco@gmail.com"
            ),
            "Permitido olhar e copiar",
            "", emptyList()
        )
    }

    private fun simpleMessage(code: Int, msg: String): ResponseMessage {
        return ResponseMessageBuilder().code(code).message(msg).build()
    }

    private fun customMessage1(): ResponseMessage {
        val map: MutableMap<String, Header> = HashMap()
        map["location"] = Header("location", "URI do novo recurso", ModelRef("string"))
        return ResponseMessageBuilder()
            .code(201)
            .message("Recurso criado")
            .headersWithDescription(map)
            .build()
    }
}