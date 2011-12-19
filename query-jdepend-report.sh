#!/bin/sh

depsof() {
  xpath  -e '//Package[UsedBy/Package/text() = "'$1'"]/@name' jdepend/report.xml  2> /dev/null | sed 's@.*name="\(.*\)"@\1@g' | sort
}

done=org.apache.commons.math.linear
deps=$(depsof $done)
count=$(echo "$deps" | wc -l)
oldcount=0

while test $oldcount -ne $count
do
    for i in $deps
    do
        if echo "$done" | grep -q $i
            then
            echo "skip "$i
        else
            echo "process "$i
            deps=$( (echo "$deps" ; depsof $i) | sort | uniq)
            done=$( echo "$done" ; echo $i)
        fi
    done
    oldcount=$count
    count=$(echo "$deps" | wc -l)
done

echo "$deps"


#xpath  -e '//Package[UsedBy/Package/text() = (//Package[UsedBy/Package/text() = "org.apache.commons.math.linear"]/@name)]/@name' jdepend/report.xml

#xpath  -e '//Package[UsedBy/Package/text() = "org.apache.commons.math.linear"]/@name' jdepend/report.xml


#touch manifest.mf
#ln -s target/classes build