package com.example.NVBite.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.NVBite.data.models.Orphanage
import com.example.NVBite.ui.starter.MainActivity

object Constants {
    fun handleUnauthorizedUser(activity: Activity) {
        activity.apply {
            finishAffinity()
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    val orphanages = listOf(
        Orphanage(
            name = "Panti Asuhan - PYI Yatim & Zakat Cab. Ngagel Surabaya",
            address = "Jl. Ngagel Madya Jl. Kertajaya No.83, Baratajaya, Kec. Gubeng, Surabaya, Jawa Timur 60282",
            phone = "085173413813",
            mapUrl = "https://maps.app.goo.gl/u9tp71sG5fDZAmtE8"
        ),
        Orphanage(
            name = "Panti Asuhan Yatim Piatu Al Mu'Min",
            address = "Jl Wisma Lidah Kulon B No.125, Lidah Kulon, Kec. Lakarsantri, Surabaya, Jawa Timur 60213",
            phone = "0317526153",
            mapUrl = "https://maps.app.goo.gl/iZn9y3W5Fz68jAQL6"
        ),
        Orphanage(
            name = "Panti Asuhan Sola Gratia",
            address = "Jl. Perintis III No.14B, Tambak Kidul, Tambakrejo, Kec. Waru, Kabupaten Sidoarjo, Jawa Timur 61256",
            phone = "081938493146",
            mapUrl = "https://maps.app.goo.gl/58ERRQCk7uPiQUCLA"
        ),
        Orphanage(
            name = "Yayasan Anak Sholeh Surabaya",
            address = "Gg. X No.6, Medokan Ayu, Kec. Rungkut, Surabaya, Jawa Timur 60295",
            phone = "081333534931",
            mapUrl = "https://maps.app.goo.gl/b7Tg46segGv2N2Zs6"
        ),
        Orphanage(
            name = "Panti Asuhan Yatim Cahaya Insani Surabaya",
            address = "Jl. Gubeng Kertajaya III No.3, Gubeng, Kec. Gubeng, Surabaya, Jawa Timur 60281",
            phone = "0315022818",
            mapUrl = "https://maps.app.goo.gl/H38EQ9qffXmRJmBn8"
        ),
        Orphanage(
            name = "UPTD Liponsos Kalijudan",
            address = "Mulyorejo, Jalan Villa Kalijudan Indah XV No.Kav. 2-4, Kalijudan, Surabaya, Jawa Timur 60114",
            phone = "0313818341",
            mapUrl = "https://maps.app.goo.gl/z6ZPTGjWvhB5Quve7"
        ),
        Orphanage(
            name = "Panti Asuhan Darussalam",
            address = "Jl. Tegal Mulyorejo Baru No.70-71, Kejawaan Putih Tamba, Kec. Mulyorejo, Surabaya, Jawa Timur 60112",
            phone = "081252341427",
            mapUrl = "https://maps.app.goo.gl/H1uyzBJWGVjjofRU6"
        ),
        Orphanage(
            name = "Yayasan panti asuhan al insan 89",
            address = "Undaan Peneleh IV No.7, Peneleh, Kec. Genteng, Surabaya, Jawa Timur 60274",
            phone = "081545454019",
            mapUrl = "https://maps.app.goo.gl/9Pf5yUDCv4Ygg9ei6"
        ),
        Orphanage(
            name = "Panti Asuhan Muhammadiyah Kenjeran",
            address = "Jl. Tambak Wedi Baru No.77, Tambak Wedi, Kec. Kenjeran, Surabaya, Jawa Timur 60126",
            phone = "0313721589",
            mapUrl = "https://maps.app.goo.gl/w9FfCrmE7Z4x2UNH8"
        ),
        Orphanage(
            name = "Yayasan PPAY Al Amal",
            address = "Jl. Wonosari Lor No.52, Wonokusumo, Kec. Semampir, Surabaya, Jawa Timur 60154",
            phone = "0313714911",
            mapUrl = "https://maps.app.goo.gl/6w4t5Bknf6UT8aw46"
        ),
        Orphanage(
            name = "Panti Asuhan Undaan Surabaya",
            address = "PPWV+569, Jl. Undaan Kulon, Peneleh, Kec. Genteng, Surabaya, Jawa Timur 60274",
            phone = "0315341627",
            mapUrl = "https://maps.app.goo.gl/ELyFWj3dPq79mqYb7"
        ),
        Orphanage(
            name = "Panti Asuhan Mahbubiyah",
            address = "Jl. Bentul I No.6, Jagir, Kec. Wonokromo, Surabaya, Jawa Timur 60244",
            phone = "0318483082",
            mapUrl = "https://maps.app.goo.gl/n4MwWpvCiVPeviFD8"
        ),
        Orphanage(
            name = "Panti Asuhan Bukti Kasih",
            address = "Jl. Tenggilis Mejoyo Selatan V No.1, Surabaya, Jawa Timur, Surabaya, Jawa Timur 60299",
            phone = "085230075000",
            mapUrl = "https://maps.app.goo.gl/i9Mfy432zvecLhRp7"
        ),
        Orphanage(
            name = "Panti Asuhan Wachid Hasyim",
            address = "Jl. Kyai Satari No.2, Rungkut Menanggal, Kec. Gn. Anyar, Surabaya, Jawa Timur 60293",
            phone = "0318415943",
            mapUrl = "https://maps.app.goo.gl/bXtrAj8e4FCy7fY79"
        )
    )
}