It works with annotations.

Given the following java files in directory src/test/java

    @Imhotep(level="Service")
    public void FirstTest {

    	@Test public void one() { }
    	@Test public void two() { }
    	@Test public void three() { }
    }

    @Imhotep(level="Domain")
    public void SecondTest {

    	@Test public void one() { }
    }

When I ask Imhotep to build the pyramid

    <project name="imhotep report" default="report" basedir=".">

        <property name="src.dir"  location="src/test/java"/>
        <property name="pyramid.dir"  location="reports"/>
        <property name="imhotep.jar.dir"  location="lib"/>

        <target name="init">
            <tstamp/>
            <mkdir dir="${pyramid.dir}"/>
        </target>

        <taskdef name="imhotep" classname="imhotep.ant.ImhotepAntTask">
            <classpath path="${imhotep.jar.dir}/imhotep-20140319.jar" />
        </taskdef>

        <target name="report" depends="init" >
            <imhotep sourcedir="${src.dir}" destdir="${pyramid.dir}">
                <level name="Domain"/>
                <level name="Service"/>
            </imhotep>
        </target>


    </project>

Then Imhotep build the following reports/pyramid.html

	Pyramid: src/test/java

	Domain: 1
	Service: 3