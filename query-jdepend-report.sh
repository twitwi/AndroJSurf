#!/bin/sh

verbose=1
test "x$1" = x || verbose=0
info() {
  test $verbose = 0 || echo "INFO: $@" 1>&2
}

cumulate() {
    done=$2
    deps=$($1 $done)
    count=$(echo "$deps" | wc -l)
    oldcount=0
    
    while test $oldcount -ne $count
    do
        for i in $deps
        do
            if echo "$done" | grep -q '^'"$i"'$'
            then
                info "skip " $i
            else
                info "process "$i
                deps=$( (echo "$deps" ; $1 $i) | sort | uniq)
                done=$( (echo "$done" ; echo $i) | sort | uniq)
            fi
    done
    oldcount=$count
    count=$(echo "$deps" | wc -l)
    done
    echo "$done"
}


where=src/main/java/org/apache/commons/math/linear
depsofclass() {
    for i in $(find $where -name \*.java)
    do
        ii=${i##*/}
        ii=${ii%.java}
        cat $where/$1.java | grep -q $ii && echo $ii
    done | sort
}
jdependreport=jdepend/report.xml
depsof() {
  xpath  -e '//Package[UsedBy/Package/text() = "'$1'"]/@name' $jdependreport  2> /dev/null | sed 's@.*name="\(.*\)"@\1@g' | sort
}

echo "PACKAGE REPORT (BASED ON JDEPEND):"
cumulate depsof org.apache.commons.math.linear

echo "FREE STYLE CLASS REPORT"
cumulate depsofclass LUDecompositionImpl
echo


#xpath  -e '//Package[UsedBy/Package/text() = (//Package[UsedBy/Package/text() = "org.apache.commons.math.linear"]/@name)]/@name' jdepend/report.xml

#xpath  -e '//Package[UsedBy/Package/text() = "org.apache.commons.math.linear"]/@name' jdepend/report.xml


#touch manifest.mf
#ln -s target/classes build