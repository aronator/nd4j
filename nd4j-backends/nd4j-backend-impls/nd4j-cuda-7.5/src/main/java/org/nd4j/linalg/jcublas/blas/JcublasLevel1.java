package org.nd4j.linalg.jcublas.blas;

import jcuda.Pointer;
import jcuda.jcublas.JCublas;
import jcuda.jcublas.JCublas2;
import org.nd4j.jita.allocator.Allocator;
import org.nd4j.jita.allocator.impl.AtomicAllocator;
import org.nd4j.linalg.api.blas.impl.BaseLevel1;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.complex.IComplexDouble;
import org.nd4j.linalg.api.complex.IComplexFloat;
import org.nd4j.linalg.api.complex.IComplexNDArray;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.DataTypeValidation;
import org.nd4j.linalg.jcublas.CublasPointer;
import org.nd4j.linalg.jcublas.context.ContextHolder;
import org.nd4j.linalg.jcublas.context.CudaContext;
import org.nd4j.nativeblas.DefaultPointerConverter;
import org.nd4j.nativeblas.NativeOps;
import org.nd4j.nativeblas.Nd4jBlas;
import org.nd4j.nativeblas.PointerConverter;

/**
 * @author Adam Gibson
 */
public class JcublasLevel1 extends BaseLevel1 {
    private Allocator allocator = AtomicAllocator.getInstance();
    private Nd4jBlas nd4jBlas = new Nd4jBlas();
    private PointerConverter pointerConverter = new DefaultPointerConverter();
    @Override
    protected float sdsdot(int N, float alpha, INDArray X, int incX, INDArray Y, int incY) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected double dsdot(int N, INDArray X, int incX, INDArray Y, int incY) {
        throw new UnsupportedOperationException();
    }



    @Override
    protected float sdot(int N, INDArray X, int incX, INDArray Y, int incY) {
        DataTypeValidation.assertSameDataType(X, Y);
        CudaContext ctx = CudaContext.getBlasContext();

        float ret = 1f;

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        ret = nd4jBlas.sdot(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX,
                    yCPointer.getDevicePointer().getNativePointer(),
                    incY);

        return ret;
    }

    @Override
    protected float sdot( int N, DataBuffer X, int offsetX, int incX, DataBuffer Y,  int offsetY, int incY){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected double ddot(int N, INDArray X, int incX, INDArray Y, int incY) {
        double ret;

        CudaContext ctx = CudaContext.getBlasContext();
        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        ret = nd4jBlas.ddot(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX,
                    yCPointer.getDevicePointer().getNativePointer(),
                    incY);

        return ret;
    }

    @Override
    protected double ddot( int N, DataBuffer X, int offsetX, int incX, DataBuffer Y,  int offsetY, int incY){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void cdotu_sub(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY, IComplexNDArray dotu) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void cdotc_sub(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY, IComplexNDArray dotc) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void zdotu_sub(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY, IComplexNDArray dotu) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void zdotc_sub(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY, IComplexNDArray dotc) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected float snrm2(int N, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        float ret;

        CublasPointer cAPointer = new CublasPointer(X,ctx);

        ret = nd4jBlas.snrm2(new long[]{ctx.getHandle().getNativePointer()}
                    ,N,
                    cAPointer.getDevicePointer().getNativePointer(),
                    incX);
        return ret;
    }

    @Override
    protected float sasum(int N, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        float ret;

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        ret = nd4jBlas.sasum(new long[]{ ctx.getHandle().getNativePointer()},
                    N,xCPointer.getDevicePointer().getNativePointer(),
                    incX);
        return ret;
    }

    @Override
    protected float sasum(int N, DataBuffer X, int offsetX, int incX){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected double dnrm2(int N, INDArray X, int incX) {
        double ret;

        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer cAPointer = new CublasPointer(X,ctx);

         ret = nd4jBlas.dnrm2(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    cAPointer.getDevicePointer().getNativePointer(),
                    incX);
        return ret;
    }

    @Override
    protected double dasum(int N, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        double ret;

        CublasPointer xCPointer = new CublasPointer(X,ctx);

        ret  = nd4jBlas.dasum(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX);
        return ret;
    }

    @Override
    protected double dasum(int N, DataBuffer X, int offsetX, int incX){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected float scnrm2(int N, IComplexNDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        float[] ret = new float[1];
        Pointer result = Pointer.to(ret);

        CublasPointer xCPointer = new CublasPointer(X,ctx);

        JCublas2.cublasScnrm2(
                    ContextHolder.getInstance().getHandle()
                    , N
                    , xCPointer.getDevicePointer()
                    , incX
                    , result);
        return ret[0];
    }

    @Override
    protected float scasum(int N, IComplexNDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        float[] ret = new float[1];
        Pointer result = Pointer.to(ret);

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        JCublas2.cublasScasum(
                    ctx.getHandle()
                    , N
                    , xCPointer.getDevicePointer()
                    , incX
                    , result);
        return ret[0];
    }

    @Override
    protected double dznrm2(int N, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected double dzasum(int N, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected int isamax(int N, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        int ret2;

        CublasPointer xCPointer = new CublasPointer(X,ctx);

        ret2 =   nd4jBlas.isamax(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX);
        return  ret2;
    }

    @Override
    protected int isamax(int N, DataBuffer X, int offsetX, int incX){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected int idamax(int N, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        int ret2;

        CublasPointer xCPointer = new CublasPointer(X,ctx);

        ret2 = nd4jBlas.idamax(new long[]{ctx.getHandle().getNativePointer()}
                  ,N,
                  xCPointer.getDevicePointer().getNativePointer(),
                  incX);
        return  ret2 ;
    }

    @Override
    protected int idamax(int N, DataBuffer X, int offsetX, int incX){
        throw new UnsupportedOperationException("not yet implemented");
    }


    @Override
    protected int icamax(int N, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int izamax(int N, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void sswap(int N, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();
        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        nd4jBlas.sswap(new long[]{ctx.getHandle().getNativePointer()},
                   N,xCPointer.getDevicePointer().getNativePointer(),
                   incX,
                   yCPointer.getDevicePointer().getNativePointer(),
                   incY);
    }

    @Override
    protected void scopy(int N, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        nd4jBlas.scopy(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX,
                    yCPointer.getDevicePointer().getNativePointer(),
                    incY);
    }

    @Override
    protected void scopy(int n, DataBuffer x, int offsetX, int incrX, DataBuffer y, int offsetY, int incrY ){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void saxpy(int N, float alpha, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xAPointer = new CublasPointer(X,ctx);
        CublasPointer xBPointer = new CublasPointer(Y,ctx);

        long[] p =  new long[]{ctx.getHandle().getNativePointer()};
/*            System.out.println("P[0]: " + p[0]);
            System.out.println("P[1]: " + p[1]);
            System.out.println("X: " + xAPointer.getPointer().getNativePointer());
            System.out.println("Y: " + xBPointer.getPointer().getNativePointer());*/
         nd4jBlas.saxpy(p,
                  N,
                  alpha,
                  xAPointer.getDevicePointer().getNativePointer(),
                  incX,
                  xBPointer.getDevicePointer().getNativePointer(),
                  incY);

//         allocator.tickDeviceWrite(Y);

//         allocator.tackDevice(X);
//         allocator.tackDevice(Y);
    }

    @Override
    protected void saxpy( int N, float alpha, DataBuffer x, int offsetX, int incrX, DataBuffer y, int offsetY, int incrY ){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void dswap(int N, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        nd4jBlas.dswap(new long[]{ctx.getHandle().getNativePointer()},
                  N,
                  xCPointer.getDevicePointer().getNativePointer(),
                  incX,
                  yCPointer.getDevicePointer().getNativePointer(),
                  incY);
    }

    @Override
    protected void dcopy(int N, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xCPointer = new CublasPointer(X,ctx);
        CublasPointer yCPointer = new CublasPointer(Y,ctx);

        nd4jBlas.dcopy(new long[]{ctx.getHandle().getNativePointer()},
                N,xCPointer.getDevicePointer().getNativePointer(),
                    incX,
                    yCPointer.getDevicePointer().getNativePointer(),
                    incY);
    }

    @Override
    protected void dcopy(int n, DataBuffer x, int offsetX, int incrX, DataBuffer y, int offsetY, int incrY ){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void daxpy(int N, double alpha, INDArray X, int incX, INDArray Y, int incY) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xAPointer = new CublasPointer(X,ctx);

        CublasPointer xBPointer = new CublasPointer(Y,ctx);

        nd4jBlas.daxpy(new long[]{ctx.getHandle().getNativePointer()}
            ,N,alpha,xAPointer.getDevicePointer().getNativePointer(),
                    incX,xBPointer.getDevicePointer().getNativePointer(),
                    incY);
    }

    @Override
    protected void daxpy( int N, double alpha, DataBuffer x, int offsetX, int incrX, DataBuffer y, int offsetY, int incrY ){
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void cswap(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void ccopy(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void caxpy(int N, IComplexFloat alpha, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void zswap(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void zcopy(int N, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void zaxpy(int N, IComplexDouble alpha, IComplexNDArray X, int incX, IComplexNDArray Y, int incY) {
        throw new UnsupportedOperationException();


    }

    @Override
    protected void srotg(float a, float b, float c, float s) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void srotmg(float d1, float d2, float b1, float b2, INDArray P) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void srot(int N, INDArray X, int incX, INDArray Y, int incY, float c, float s) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void srotm(int N, INDArray X, int incX, INDArray Y, int incY, INDArray P) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void drotg(double a, double b, double c, double s) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void drotmg(double d1, double d2, double b1, double b2, INDArray P) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void drot(int N, INDArray X, int incX, INDArray Y, int incY, double c, double s) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void drotm(int N, INDArray X, int incX, INDArray Y, int incY, INDArray P) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void sscal(int N, float alpha, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();

        CublasPointer xCPointer = new CublasPointer(X, ctx);

        nd4jBlas.sscal(new long[]{ctx.getHandle().getNativePointer()},
                 N,
                 alpha,
                 xCPointer.getDevicePointer().getNativePointer(),
                 incX);
    }

    @Override
    protected void dscal(int N, double alpha, INDArray X, int incX) {
        CudaContext ctx = CudaContext.getBlasContext();
        CublasPointer xCPointer = new CublasPointer(X,ctx);

        nd4jBlas.dscal(new long[]{ctx.getHandle().getNativePointer()},
                    N,
                    alpha,
                    xCPointer.getDevicePointer().getNativePointer(),
                    incX);
    }

    @Override
    protected void cscal(int N, IComplexFloat alpha, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void zscal(int N, IComplexDouble alpha, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();


    }

    @Override
    protected void csscal(int N, float alpha, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void zdscal(int N, double alpha, IComplexNDArray X, int incX) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supportsDataBufferL1Ops() {
        return false;
    }
}
