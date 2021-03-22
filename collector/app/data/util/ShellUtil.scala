package data.util

import com.jcraft.jsch.{ChannelExec, JSch}

import java.io.{BufferedReader, ByteArrayOutputStream, InputStreamReader}

/**
 * @author jleo
 * @date 2021/3/9
 */
class ShellUtil {

  def execute(ip: String, port: Int, userName: String, password: String, command: String): String = {
    val jsch = new JSch
    val session = jsch.getSession(userName, ip, port)
    session.setConfig("StrictHostKeyChecking", "no")
    session.setPassword(password)
    session.connect()
    val channelExec: ChannelExec = session.openChannel("exec").asInstanceOf[ChannelExec]
    val in = channelExec.getInputStream
    val out = new ByteArrayOutputStream()
    //设置环境变量
    channelExec.setCommand(command)
    channelExec.connect()
    val read = new BufferedReader(new InputStreamReader(in));
    var result = read.readLine()
    if (session != null) {
      session.disconnect();
    }
    if (read != null) {
      read.close();
    }
    if (out != null) {
      out.close();
    }
    if (in != null) {
      in.close();
    }
    result
  }
}
