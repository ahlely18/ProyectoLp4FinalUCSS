<?php

require_once 'Conexion.php';

class Negocio {

    // lista de rutas
    public function lisRutas() {
        $obj = new Conexion();
        $sql = "select rutcod,rutnom from ruta";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("cod" => $f[0], "nome" => $f[1]));
        }
        return $response;
    }
    
    // lista de choferes
    public function lisChofer() {
        $obj = new Conexion();
        $sql = "select idcod,chonom,chosba from ruta";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("cod" => $f[0], "nome" => $f[1],"sba"=>$f[2]));
        }
        return $response;
    }

    // lista de viaje segun ruta
    public function lisViajexRuta($cod) {
        $obj = new Conexion();
        $sql = "select vianro,busnro,idcod,viafch,viahrs,cosvia from viaje where rutcod='$cod'";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("cod" => $f[0], "bus" => $f[1], "chofcod" => $f[2], "fecha" => $f[3], "hora" => $f[4],"costo" => $f[5]));
        }
        return $response;
    }

    // lista de pasajeros segun viaje
    public function lisPasajeroxViaje($cod) {
        $obj = new Conexion();
        $sql = "select bolnro,nom_pas,nro_asi,tipo,pago from pasajeros where vianro='$cod'";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("cod" => $f[0], "nombre" => $f[1], "nroasi" => $f[2], "tipo" => $f[3], "pago" => $f[4]));
        }
        return $response;
    }
    
     // lista de viajes x chofer
    public function lisViajexchofer($cod) {
        $obj = new Conexion();
        $sql = "select vianro,busnro,rutcod,viafch,viahrs,cosvia from viaje where idcod='$cod'";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("cod" => $f[0], "bus" => $f[1], "rutcod" => $f[2], "fecha" => $f[3], "hora" => $f[4],"costo" => $f[5]));
        }
        return $response;
    }
    

    // adicionar pasajero
    public function adicionPasajero($codviaje, $nome, $tipo, $asiento, $pago) {
        $obj = new Conexion();
        $sql = "call spadicion('$codviaje','$nome','$tipo','$asiento',$pago)";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        if ($res)
            $response["dato"] = "ok";
        else
            $response["dato"] = "error";
        return $response;
    }

    // login
    public function login($user, $code) {
        $obj = new Conexion();
        $sql = "select nombre,usuario,acceso from usuario where usuario='$user' and "
                . " code='$code' ";
         $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("nome" => $f[0], "user" => $f[1], "acceso" => $f[2]));
        }
        return $response;
    }
    // crear cuenta 
    public function adicionCuenta($code, $usuario, $nombre, $correo, $telefono) {
        $obj = new Conexion();
        $sql = "call spcreateaccount('$code','$usuario','$nombre','$correo','$telefono','NO')";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        if ($res)
            $response["dato"] = "ok";
        else
            $response["dato"] = "error";
        return $response;
    }
    
    // lista de asientos x viaje
    public function lisAsientosxViaje($cod) {
        $obj = new Conexion();
        $sql = "select nro_asi from pasajeros where vianro='$cod'";
        $res = mysqli_query($obj->Conecta(), $sql) or die(mysqli_error($obj->Conecta()));
        $response["dato"] = array();
        while ($f = mysqli_fetch_array($res)) {
            array_push($response["dato"], array("asi" => $f[0]));
        }
        return $response;
    }
}

?>