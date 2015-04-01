# spark_cuda
Heterogenous Cluster - use GPU (CUDA) for highly intensive computing.


## INIT
Download spark and compile it - I'm using scala 2.11 so need to switch spark to proper one:
<pre>
dev/change-version-to-2.11.sh
mvn -Pyarn -Phadoop-2.4 -Dscala-2.11 -DskipTests clean package
</pre>
Test if is working:
./bin/spark-shell 
<pre>
....
</pre>

## Build this project
<pre>
sbt package
sbt eclipse  // for eclipse
</pre>


## Run it
<pre>
../../spark-1.3.0/bin/spark-submit --class "SimpleApp" --master local[4] target/scala-2.11/spark_cuda_2.11-1.0.0.jar
</pre>
