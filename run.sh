action=$1
javaOpt="-Xms512m -Xmx4096m -server"

build(){
  echo "开始编译"
  mvn package -DskipTests -Pprod
  echo "编译完成"
  exit 0
}

start(){
   if [ -n "$pid" ]; then
      echo "已有进程正在运行，请使用restart重启"
      exit 0;
  fi
  nohup java $javaOpt -jar "./astro-air-server/target/astro-air-server-0.9.5.jar" >/dev/null 2>&1 &
  echo "开始启动应用..."
}

stop(){
  if [ -z "$pid" ]; then
      echo "没有运行的进程，停止失败"
  else
    kill $pid
    pid=""
    echo "停止成功"
  fi
}

restart(){
  stop
  sleep 2
  start
}

if [ `whoami` != "www" ]; then
  echo "请切换到www用户执行此命令"
  exit 0
fi

echo '是否确认已更新版本库(n):[y/n]?'
read gitUpdate
if [ -x "$gitUpdate" ]; then
  gitUpdate="n"
fi

if [ "$gitUpdate" = "y" -o "$gitUpdate" = "Y" -o "$gitUpdate" = "yes"  -o "$gitUpdate" = "YES" ]; then
  git pull
fi

echo "查询是否已存在进程"
pid=`lsof -i :8010 |awk 'NR>=2{print $2}'`
echo "正在运行的进程id:$pid"
if [ "$action" = "build" ]; then
  build
elif [ "$action" = "start" ]; then
  if [ "$gitUpdate" = "y" -o "$gitUpdate" = "Y" -o "$gitUpdate" = "yes"  -o "$gitUpdate" = "YES" ]; then
    build
  fi
  start
elif [ "$action" = "stop" ]; then
  stop
elif [ "$action" = "restart" ]; then
  restart
fi