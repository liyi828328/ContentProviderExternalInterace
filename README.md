# ContentProviderExternalInterace
ContentProvider 跨进程调用

#文件读取
  public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {}

#call方法实现跨进程调用
  public Bundle call(String method, String arg, Bundle extras) {}
