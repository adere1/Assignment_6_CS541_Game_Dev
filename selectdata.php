 <?php

         $con = mysqli_connect("127.0.0.1","root","superadmin","game_schema");
         if (!$con)
           {
             die('Could not connect: ' . mysql_error());
           }
           
		   
           $v1 = urldecode($_POST['name']);
		   print($v1);
            
           $v2 = urldecode($_POST['getreq']);
		   if($v2 == 'true'){
                $i=mysqli_query($con,"select * from notes where noteuser='$v1'");
                $check='';
                if($row = $i->fetch_array())
                {  
                  $r[]=$row;                  
                }else{                              
                      $r["re"]="Record is not available";
                      print(json_encode($r));          
                   
				}  	 
                 $r["re"]="success";
                 print(json_encode($r));				 
		   }else{
			   
			   $v3 = urldecode($_POST['data']);
			   $con->query("update notes set notedata = '$v3' where noteuser='$v1'");               
			   $r["re"]="success1";
               print(json_encode($r));
			   
		   }




 mysqli_close($con);
               
    ?> 