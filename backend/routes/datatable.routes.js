const express = require('express');
const router = express.Router();
const db = require("../db");
const fs = require('fs');
const Json2csvParser = require("json2csv").Parser;
const json2csvParser = new Json2csvParser({ header: true });

router.get('/', async function (req, res, next) {

        let rows = (await db.query('SELECT public."drzava"."naziv",' + 
              'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
              'public."drzava"."puniNaziv" AS "puni_naziv",' +
              'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
              'public."drzava"."povrsina" AS "povrsina_u_km2",' +
              'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
              'public."drzava"."glavniGrad" AS "glavni_grad",' +
              'public."drzava"."nazivHimne" AS "himna",' +
              'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
              'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
              'public."jezik"."naziv" AS "jezik",' +
              'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
              'public."valuta"."naziv" AS "valuta"' +
            'FROM public."drzavaJezik", public."drzava", public."jezik",' +
                    'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
            'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
                'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
                'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
                'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
                'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"')).rows;

    //rows je polje objekata

    res.render('datatable', {
        title: 'Datatable',
        linkActive: 'datatable',
        rows: rows,
    });
});

router.post('/', async function (req, res, next) {
    let searchText = req.body.searchText;
    let searchAttribute = req.body.searchAttribute;

    let rows;
    
    if (searchAttribute == "wildcard") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
              'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
              'public."drzava"."puniNaziv" AS "puni_naziv",' +
              'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
              'public."drzava"."povrsina" AS "povrsina_u_km2",' +
              'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
              'public."drzava"."glavniGrad" AS "glavni_grad",' +
              'public."drzava"."nazivHimne" AS "himna",' +
              'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
              'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
              'public."jezik"."naziv" AS "jezik",' +
              'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
              'public."valuta"."naziv" AS "valuta"' +
            'FROM public."drzavaJezik", public."drzava", public."jezik",' +
                    'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
            'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
                'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
                'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
                'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
                'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
                'AND (UPPER(public."drzava"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' + 
                '  OR UPPER(public."drzava"."ISOozn"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."puniNaziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."datumUlaskaEU"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."povrsina"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."brojStanovnika"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."glavniGrad"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzava"."nazivHimne"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzavniVrh"."uloga"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzavniVrh"."punoIme"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzavaJezik"."ISOoznJez"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."jezik"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."drzavaValuta"."ISOoznVal"::text) LIKE \'%' + searchText.toUpperCase() + '%\'' +
                '  OR UPPER(public."valuta"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\' );' )).rows;


    } else if (searchAttribute == "naziv") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
              'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
              'public."drzava"."puniNaziv" AS "puni_naziv",' +
              'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
              'public."drzava"."povrsina" AS "povrsina_u_km2",' +
              'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
              'public."drzava"."glavniGrad" AS "glavni_grad",' +
              'public."drzava"."nazivHimne" AS "himna",' +
              'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
              'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
              'public."jezik"."naziv" AS "jezik",' +
              'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
              'public."valuta"."naziv" AS "valuta"' +
            'FROM public."drzavaJezik", public."drzava", public."jezik",' +
                    'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
            'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
                'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
                'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
                'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
                'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
                'AND UPPER(public."drzava"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;
                
    } else if (searchAttribute == "ISO_oznaka_drzave") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."ISOozn"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;
    
    } else if (searchAttribute == "puni_naziv") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."puniNaziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;
          
    } else if (searchAttribute == "datum_ulaska_u_EU") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."datumUlaskaEU"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "povrsina_u_km2") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."povrsina"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "broj_stanovnika") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."brojStanovnika"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "glavni_grad") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."glavniGrad"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "himna") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzava"."nazivHimne"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "drzavni_vrh") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND (UPPER(public."drzavniVrh"."uloga") LIKE \'%' + searchText.toUpperCase() + '%\' ' + 
          '  OR UPPER(public."drzavniVrh"."punoIme"::text) LIKE \'%' + searchText.toUpperCase() + '%\'); ')).rows;

    } else if (searchAttribute == "ISO_oznaka_jezika") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzavaJezik"."ISOoznJez"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "jezik") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."jezik"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "ISO_oznaka_valute") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."drzavaValuta"."ISOoznVal"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    } else if (searchAttribute == "valuta") {
        rows = (await db.query('SELECT public."drzava"."naziv",' + 
        'public."drzava"."ISOozn" AS "ISO_oznaka_drzave",' + 
        'public."drzava"."puniNaziv" AS "puni_naziv",' +
        'public."drzava"."datumUlaskaEU" AS "datum_ulaska_u_EU",' +
        'public."drzava"."povrsina" AS "povrsina_u_km2",' +
        'public."drzava"."brojStanovnika" AS "broj_stanovnika",' +
        'public."drzava"."glavniGrad" AS "glavni_grad",' +
        'public."drzava"."nazivHimne" AS "himna",' +
        'public."drzavniVrh"."uloga" || \' \' || public."drzavniVrh"."punoIme" AS "drzavni_vrh",' +
        'public."drzavaJezik"."ISOoznJez" AS "ISO_oznaka_jezika",' +
        'public."jezik"."naziv" AS "jezik",' +
        'public."drzavaValuta"."ISOoznVal" AS "ISO_oznaka_valute",' +
        'public."valuta"."naziv" AS "valuta"' +
      'FROM public."drzavaJezik", public."drzava", public."jezik",' +
              'public."drzavaValuta", public."valuta", public."drzavniVrh"' +
      'WHERE public."drzavaJezik"."ISOoznDrz" = public."drzava"."ISOozn"' +
          'AND public."jezik"."ISOozn" = public."drzavaJezik"."ISOoznJez"' +
          'AND public."drzava"."ISOozn" = public."drzavaValuta"."ISOoznDrz"' +
          'AND public."drzavaValuta"."ISOoznVal" = public."valuta"."ISOozn"' +
          'AND public."drzava"."ISOozn" = public."drzavniVrh"."ISOoznDrz"' + 
          'AND UPPER(public."valuta"."naziv"::text) LIKE \'%' + searchText.toUpperCase() + '%\'; ')).rows;

    }

    let tableContent = "";

    for (let row of rows) {
        tableContent = tableContent + "<tr>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.naziv);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.ISO_oznaka_drzave);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.puni_naziv);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.datum_ulaska_u_EU);
        tableContent = tableContent+ "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.povrsina_u_km2);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.broj_stanovnika);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.glavni_grad);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.himna);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.drzavni_vrh);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.ISO_oznaka_jezika);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.jezik);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.ISO_oznaka_valute);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "<td>";
        tableContent = tableContent + new String(row.valuta);
        tableContent = tableContent + "</td>";
        tableContent = tableContent + "</tr>";
    }

    fs.writeFile("./public/resources/drzaveEUfiltrirano.json", JSON.stringify(rows), function(err) {
        if (err) throw err;
        console.log('JSON spremljen');
    });

    console.log(rows);
    const csvData = json2csvParser.parse(rows);

    fs.writeFile("./public/resources/drzaveEUfiltrirano.csv", csvData, function(err) {
        if (err) throw err;
        console.log('CSV spremljen');
    });

    res.send(
        {
            response: new String(tableContent), 
            filteredRows: JSON.stringify(rows)
        }
    );
});



module.exports = router;