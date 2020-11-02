
function thumb() {
 for file in `find $1`; do
  if [ -d $file -a "$file" != "qrcode" ]; then
    echo "folder:$file"
    thumb $1/$file
  elif [ -f $file ]; then
    if [[ ! "$file" =~ "_thumb" ]]; then
      prefix=${file%.*}
      suffix=${file#*.}
      if [ "${suffix,,}" = "png" -o "${suffix,,}" = "jpg" -o "${suffix,,}" = "jpeg" -o "${suffix,,}" = "bmp" -o "${suffix,,}" = "gif" ]; then
        thumbPath="${prefix}_thumb.${suffix}"
        if [ ! -f "$thumbPath" ]; then
          echo "$file thumb to $thumbPath"
          convert -resize 300x300 $file $thumbPath
        fi
      fi
    fi
  fi
 done
}

thumb /data/test/page/astro-air/upload