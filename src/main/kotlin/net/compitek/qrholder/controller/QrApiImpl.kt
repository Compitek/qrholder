package net.compitek.qrholder.controller

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import net.compitek.qrholder.api.QrApi
import net.compitek.qrholder.model.CommonReportRow
import net.compitek.qrholder.model.QR
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.*

@Controller
class QrApiImpl: QrApi  {
    @ApiOperation(value = "adds a qr", nickname = "addQr", notes = "adds a new qr, if there is no the same uuid in the database", tags = ["admins"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Item created"), ApiResponse(code = 400, message = "Bad input parameter"), ApiResponse(code = 422, message = "Unprocessable Entity. Entity with the same uuid already created"), ApiResponse(code = 500, message = "Internal server error"), ApiResponse(code = 503, message = "Service unavailable")])
    @RequestMapping(value = ["/qr"], consumes = ["application/json"], method = [RequestMethod.POST])
    override fun addQr(body: MutableList<QR>?): ResponseEntity<Void> {
        return super.addQr(body)
    }


    @ApiOperation(value = "generate a new qr item", nickname = "generateQR", notes = "Generates and Adds a new qr item to the system", tags = ["customers"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Item created"), ApiResponse(code = 400, message = "Bad input parameter"), ApiResponse(code = 500, message = "Internal server error"), ApiResponse(code = 503, message = "Service unavailable")])
    @RequestMapping(value = ["/qr/generate"], method = [RequestMethod.POST])
    override fun generateQR(namespace: String?, description: String?): ResponseEntity<Void> {
        return super.generateQR(namespace, description)
    }

    @ApiOperation(value = "get a report about qr", nickname = "generateTheReport", notes = "get the data about quantity of qr codes", response = CommonReportRow::class, responseContainer = "List", tags = ["customers"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "common report", response = CommonReportRow::class, responseContainer = "List"), ApiResponse(code = 400, message = "Bad input parameter"), ApiResponse(code = 500, message = "Internal server error"), ApiResponse(code = 503, message = "Service unavailable")])
    @RequestMapping(value = ["/qr/report/common"], produces = ["application/json"], method = [RequestMethod.GET])
    override fun generateTheReport(): ResponseEntity<MutableList<CommonReportRow>> {
        var row = CommonReportRow(); row.count=0; row.namespace="/test";
        var rows = mutableListOf<CommonReportRow>(); rows.add(row);
        return ResponseEntity(rows, HttpStatus.OK)
    }

    @ApiOperation(value = "searches a qr", nickname = "searchQr", notes = "By passing in the appropriate options, you can search for available qr code ", response = QR::class, tags = ["suppliers"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "search results matching criteria", response = QR::class), ApiResponse(code = 400, message = "Bad input parameter"), ApiResponse(code = 500, message = "Internal server error"), ApiResponse(code = 503, message = "Service unavailable")])
    @RequestMapping(value = ["/qr/{uuid}"], produces = ["application/json"], method = [RequestMethod.GET])
    override fun searchQr(uuid: UUID?): ResponseEntity<QR> {
        return super.searchQr(uuid)
    }
}