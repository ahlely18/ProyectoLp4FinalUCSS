<?php

require_once './Negocio.php';
$opc = $_REQUEST['tag'];
if ($opc == "consulta1") {
    $obj = new Negocio();
    echo json_encode($obj->lisRutas());
}

if($opc == "consulta2"){
    $cod = $_REQUEST["cod"];
    $obj = new Negocio();
    echo json_encode($obj->lisViajexRuta($cod));
}

if($opc == "consulta3"){
    $cod = $_REQUEST["cod"];
    $obj = new Negocio();
    echo json_encode($obj->lisPasajeroxViaje($cod));
}

if($opc == "consulta4"){
    $cod = $_REQUEST["cod"];
    $obj = new Negocio();
    echo json_encode($obj->lisViajexchofer($cod));
}

if ($opc == "consulta5") {
    $obj = new Negocio();
    echo json_encode($obj->lisChofer());
}

if ($opc=="adicion") {
    $codviaje=$_REQUEST["cod"];
    $nome=$_REQUEST["nome"];
    $tipo=$_REQUEST["tipo"];
    $asiento=$_REQUEST["asi"];
    $pago=$_REQUEST["pago"];
$obj=new Negocio();
echo json_encode($obj->adicionPasajero($codviaje, $nome, $tipo, $asiento, $pago));
}

if ($opc=="login") {
    $cod=$_REQUEST["user"];
    $pas=$_REQUEST["code"];
    $obj=new Negocio();
    echo json_encode($obj->login($cod, $pas));
    
}

if ($opc=="create") {
    $code=$_REQUEST["code"];
    $usuario=$_REQUEST["user"];
    $nombre=$_REQUEST["nome"];
    $correo=$_REQUEST["correo"];
    $telefono=$_REQUEST["tel"];
$obj=new Negocio();
echo json_encode($obj->adicionCuenta($code, $usuario, $nombre, $correo, $telefono));
}

if($opc == "consulta6"){
    $cod = $_REQUEST["cod"];
    $obj = new Negocio();
    echo json_encode($obj->lisAsientosxViaje($cod));
}


?>